package com.devopsolution.code9.ui.fragments.check_in

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.devopsolution.code9.data.network.model.ApiResponse
import com.devopsolution.code9.data.network.model.User
import com.devopsolution.code9.ui.base.BaseViewModel

class CheckInViewModel(app: Application) : BaseViewModel(app) {

    val user = MutableLiveData<User>()

    fun getUserInfo(userId: String) {

        isLoading.value = true

        appRepositoryHelper.getUserInfo(userId).observeForever {
            handleResponseWhenError(it)

            if (it.isResponseSuccessful)
                user.postValue(it.responseBody!!.data)
        }
    }

    fun checkInUser(): MutableLiveData<ApiResponse<Any?>> {
        isLoading.value = true

        return appRepositoryHelper.checkInUser(user.value?.id?:"").apply {
            observeForever(this@CheckInViewModel::handleResponseWhenError)
        }
    }

}