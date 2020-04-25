package com.devopsolution.code9.ui.base

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.devopsolution.code9.common.Constants
import com.devopsolution.code9.common.extensions.appComponent
import com.devopsolution.code9.common.extensions.get
import kotlinx.android.synthetic.main.activity_main.*


abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>
    (private val mViewModelClass: Class<VM>) : AppCompatActivity(), BaseView {

    val mBinding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }

    val viewModel by lazy {
        ViewModelProvider(this).get(mViewModelClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel(viewModel)
        initLifeCycleOwner()
        observeLiveDatas()
        init(savedInstanceState)
    }

    /**
     *  You need to override this method.
     *  And you need to set viewModel to mBinding: mBinding.viewModel = viewModel
     *
     *  @param viewModel the instance of ViewModel that is related to the  activity
     */
    abstract fun initViewModel(viewModel: VM)


    override fun initLifeCycleOwner() {
        mBinding.lifecycleOwner = this
    }

    @CallSuper
    override fun observeLiveDatas() {

    }

    override fun hideKeyboard() {
        val view = currentFocus
        if (view != null) {
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        nav_host_fragment.childFragmentManager.fragments[0]
            .onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        nav_host_fragment.childFragmentManager.fragments[0]
            .onActivityResult(requestCode, resultCode, data)
    }
}





