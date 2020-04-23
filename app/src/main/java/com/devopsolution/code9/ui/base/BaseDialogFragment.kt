package com.devopsolution.code9.ui.base

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.devopsolution.code9.R
import com.devopsolution.code9.common.extensions.errorMsg
import com.google.gson.Gson
import java.io.IOException

abstract class BaseDialogFragment<VM : BaseViewModel,
        DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) : DialogFragment(), BaseView {

    lateinit var viewModel: VM
    open lateinit var mBinding: DB


    fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
    }


    private fun getViewM(): VM = ViewModelProvider(this).get(mViewModelClass)

    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        return super.onGetLayoutInflater(null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        viewModel = getViewM()
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        initDataBinding(inflater, container)

        initViewModel(viewModel)
        initLifeCycleOwner()

        super.onCreateView(inflater, container, savedInstanceState)

        return mBinding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(null)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            dialog.window!!.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, null)
        observeLiveDatas()
        viewModel.navDestinationLabel.value =
            findNavController().currentDestination?.label.toString()
        init(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {

    }

    @CallSuper
    override fun observeLiveDatas() {

        viewModel.isUpBtnClicked.observe(this, Observer {
            if (it == true) {
                viewModel.isUpBtnClicked.value = false
                dismissAllowingStateLoss()
            }
        })

        viewModel.errorMsg.observe(viewLifecycleOwner, Observer { errorMsg(it) })
        viewModel.errorMsgRes.observe(viewLifecycleOwner, Observer { errorMsg(it) })
    }

    override fun onResume() {

        // Get existing layout params for the window
        val params = dialog?.window?.attributes
        // Assign window properties to fill the parent
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params as WindowManager.LayoutParams

        super.onResume()
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



    override fun onDestroyView() {
        super.onDestroyView()
        hideKeyboard()
        viewModel.errorResponse.value = null
        viewModel.errorResponse.removeObservers(this)
    }

    override fun hideKeyboard() {
        val view = dialog?.currentFocus
        if (view != null) {
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


}