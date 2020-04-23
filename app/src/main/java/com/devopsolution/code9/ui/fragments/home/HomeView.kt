package com.devopsolution.code9.ui.fragments.home

import com.devopsolution.code9.ui.base.BaseView

interface HomeView :BaseView{

    fun initCheckOut()
    fun initFcmToken()
    fun checkout(userId: String)
}