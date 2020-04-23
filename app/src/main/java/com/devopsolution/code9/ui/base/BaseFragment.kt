package com.devopsolution.code9.ui.base


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.devopsolution.code9.common.extensions.errorMsg
import com.devopsolution.code9.common.extensions.goToActivity
import com.devopsolution.code9.common.extensions.showDialog
import com.devopsolution.code9.ui.activities.auth.AuthActivity
import com.devopsolution.code9.ui.activities.splash.SplashActivity

abstract class BaseFragment<VM : BaseViewModel,
        DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) : Fragment(),
    BaseView {

    lateinit var viewModel: VM
    open lateinit var mBinding: DB

    private fun initDataBinding(inflater: LayoutInflater, container: ViewGroup) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
    }

    private fun getViewM(): VM = ViewModelProvider(this).get(mViewModelClass)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewM()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)


        container?.let {
            initDataBinding(inflater, it)
        }
        initViewModel(viewModel)
        initLifeCycleOwner()
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveDatas()
        viewModel.navDestinationLabel.value =
            findNavController().currentDestination?.label.toString()
        init(savedInstanceState)
    }

    @CallSuper
    override fun observeLiveDatas() {
        viewModel.isUpBtnClicked.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                viewModel.isUpBtnClicked.value = false
                findNavController().navigateUp()
            }
        })

        viewModel.errorMsg.observe(viewLifecycleOwner, Observer { showDialog(it, {}) })
        viewModel.errorMsgRes.observe(
            viewLifecycleOwner,
            Observer { showDialog(getString(it), {}) })

        viewModel.isLogoutRequired.observe(viewLifecycleOwner, Observer {

            if (it) {
                viewModel.isLogoutRequired.value = false
                goToActivity(AuthActivity::class.java)
                activity?.finish()
            }
        })
    }

    override fun initLifeCycleOwner() {
        mBinding.lifecycleOwner = this
    }


    /**
     *  You need to override this method.
     *  And you need to set viewModel to mBinding: mBinding.viewModel = viewModel
     *
     *  @param viewModel the instance of ViewModel that is related to the  activity
     */
    abstract fun initViewModel(viewModel: VM)


    override fun hideKeyboard() {
        val view = activity?.currentFocus

        if (view != null) {
            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


}
