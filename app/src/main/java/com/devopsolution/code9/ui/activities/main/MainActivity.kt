package com.devopsolution.code9.ui.activities.main

import android.os.Bundle
import com.devopsolution.code9.R
import com.devopsolution.code9.databinding.ActivityMainBinding
import com.devopsolution.code9.ui.base.BaseActivity

class MainActivity : MainView,
    BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class.java) {

    override fun getLayoutRes() = R.layout.activity_main


    override fun initViewModel(viewModel: MainViewModel) {
        mBinding.viewModel = viewModel
    }

    override fun init(savedInstanceState: Bundle?) {

    }




}