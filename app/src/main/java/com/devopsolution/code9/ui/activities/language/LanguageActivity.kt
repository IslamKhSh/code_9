package com.devopsolution.code9.ui.activities.language

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.devopsolution.code9.R
import com.devopsolution.code9.common.extensions.goToActivity
import com.devopsolution.code9.common.utils.Localization
import com.devopsolution.code9.databinding.ActivityLanguageBinding
import com.devopsolution.code9.databinding.ActivitySplashBinding
import com.devopsolution.code9.ui.activities.auth.AuthActivity
import com.devopsolution.code9.ui.activities.main.MainActivity
import com.devopsolution.code9.ui.adapters.LanguagesAdapter
import com.devopsolution.code9.ui.base.BaseActivity

class LanguageActivity : LanguageView,
    BaseActivity<LanguageViewModel, ActivityLanguageBinding>(LanguageViewModel::class.java) {

    override fun getLayoutRes() = R.layout.activity_language


    override fun initViewModel(viewModel: LanguageViewModel) {
        mBinding.viewModel = viewModel
    }


    override fun init(savedInstanceState: Bundle?) {
        mBinding.recyclerLanguage.run {
            layoutManager = LinearLayoutManager(this@LanguageActivity)
            adapter = LanguagesAdapter(viewModel.availableLangList)
        }

        mBinding.btnConfirm.setOnClickListener {
            Localization.setLanguage(this, Localization.Language.ENGLISH.value)
            goToActivity(if (viewModel.storedUserId.isNullOrEmpty()) AuthActivity::class.java else MainActivity::class.java)
            finish()
        }
    }
}