package com.devopsolution.code9.ui.fragments.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.devopsolution.code9.data.network.model.Dashboard
import com.devopsolution.code9.ui.base.BaseViewModel

class HomeViewModel(app: Application) : BaseViewModel(app) {

    val notActiveFeatureClicked = MutableLiveData(false)

    val dashboard = MutableLiveData<Dashboard>()

    fun getUserDashboard() {
        isLoading.value = true

        appRepositoryHelper.getDashBoard().observeForever {
            handleResponseWhenError(it)

            if (it.isResponseSuccessful)
                dashboard.postValue(it.responseBody!!.data)
        }
    }

    fun setNotificationToken(token: String, deviceId: String) {

        if (token == appRepositoryHelper.getFcmToken())
            return

        isLoading.value = true

        appRepositoryHelper.registerToken(token, deviceId).observeForever {
            handleResponseWhenError(it)

            if (it.isResponseSuccessful)
                appRepositoryHelper.setFcmToken(token)
        }
    }

    fun onNotActiveFeatureClicked() {
        notActiveFeatureClicked.value = true
    }

}