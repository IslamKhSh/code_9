package com.devopsolution.code9.ui.activities.auth

import android.os.Bundle
import com.devopsolution.code9.R
import com.devopsolution.code9.databinding.ActivityAuthBinding
import com.devopsolution.code9.databinding.ActivityAuthBindingImpl
import com.devopsolution.code9.ui.base.BaseActivity

class AuthActivity : BaseActivity<AuthViewModel, ActivityAuthBinding>(AuthViewModel::class.java),
    AuthView {


    override fun getLayoutRes() = R.layout.activity_auth


    override fun initViewModel(viewModel: AuthViewModel) {
        mBinding.viewModel = viewModel
    }


    override fun init(savedInstanceState: Bundle?) {
    }
}