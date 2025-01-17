package com.devopsolution.code9.ui.activities.splash

import android.os.Bundle
import com.devopsolution.code9.R
import com.devopsolution.code9.common.extensions.goToActivity
import com.devopsolution.code9.common.utils.Localization
import com.devopsolution.code9.databinding.ActivityAuthBinding
import com.devopsolution.code9.databinding.ActivitySplashBinding
import com.devopsolution.code9.ui.activities.auth.AuthActivity
import com.devopsolution.code9.ui.activities.language.LanguageActivity
import com.devopsolution.code9.ui.activities.main.MainActivity
import com.devopsolution.code9.ui.base.BaseActivity

class SplashActivity :
    BaseActivity<SplashViewModel, ActivitySplashBinding>(SplashViewModel::class.java),
    SplashView {


    override fun getLayoutRes() = R.layout.activity_splash


    override fun initViewModel(viewModel: SplashViewModel) {
        mBinding.viewModel = viewModel
    }


    override fun init(savedInstanceState: Bundle?) {
        val targetActivity = when {
            Localization.getStoredLanguage(this) == -1 -> LanguageActivity::class.java
            viewModel.storedUserId.isNullOrEmpty() -> AuthActivity::class.java
            else -> MainActivity::class.java
        }

        goToActivity(targetActivity)
        finish()
    }
}