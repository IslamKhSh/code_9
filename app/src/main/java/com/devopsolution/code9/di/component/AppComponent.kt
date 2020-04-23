package com.devopsolution.code9.di.component

import android.content.Context
import android.content.SharedPreferences
import com.devopsolution.code9.Code9App
import com.devopsolution.code9.di.module.AppModule
import com.devopsolution.code9.di.module.NetworkModule
import com.devopsolution.code9.ui.base.BaseViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    fun app(): Code9App
    fun context(): Context
    fun inject(baseViewModel: BaseViewModel)
    fun getSharedPreference(): SharedPreferences

}