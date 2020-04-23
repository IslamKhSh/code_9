package com.devopsolution.code9.ui.fragments.login

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.devopsolution.code9.R
import com.devopsolution.code9.common.extensions.goToActivity
import com.devopsolution.code9.databinding.FragmentLoginBinding
import com.devopsolution.code9.ui.activities.main.MainActivity
import com.devopsolution.code9.ui.base.BaseFragment

class LoginFragment :
    BaseFragment<LoginViewModel, FragmentLoginBinding>(LoginViewModel::class.java), LoginView {

    override fun getLayoutRes() = R.layout.fragment_login

    override fun initViewModel(viewModel: LoginViewModel) {
        mBinding.viewModel = viewModel
    }


    override fun init(savedInstanceState: Bundle?) {
        initForgetPassword()
        initSignUp()
        initLogin()
    }

    override fun initForgetPassword() {
        mBinding.tvForgetPass.setOnClickListener {
            Toast.makeText(context, R.string.coming_soon, Toast.LENGTH_SHORT).show()
        }
    }

    override fun initSignUp() {
        mBinding.tvSignUp.setOnClickListener {
            mBinding.tvForgetPass.performClick()
        }
    }

    override fun initLogin() {
        mBinding.btnLogin.setOnClickListener {
            if (viewModel.validateLoginData(mBinding.root as ViewGroup)) {
                viewModel.login(mBinding.etIdNumber.text.toString(), mBinding.etPassword.text.toString())
                    .observe(viewLifecycleOwner, Observer {
                        if (it.isResponseSuccessful){
                            goToActivity(MainActivity::class.java)
                            activity?.finish()
                        }
                    })
            }
        }
    }
}