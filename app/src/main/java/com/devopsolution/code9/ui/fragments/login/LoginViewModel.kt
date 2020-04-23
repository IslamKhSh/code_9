package com.devopsolution.code9.ui.fragments.login

import android.app.Application
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.devopsolution.code9.common.Enums
import com.devopsolution.code9.common.extensions.forEach
import com.devopsolution.code9.data.network.model.ApiResponse
import com.devopsolution.code9.data.network.model.LoginRequest
import com.devopsolution.code9.data.network.model.User
import com.devopsolution.code9.ui.base.BaseViewModel
import com.devopsolution.code9.ui.customs.CustomEditText

class LoginViewModel(app: Application) : BaseViewModel(app) {

    val userType = MutableLiveData(Enums.UserTypeEnum.Citizen)

    fun setUserTypeValue(type: Enums.UserTypeEnum) {
        userType.value = type
    }

    fun validateLoginData(root: ViewGroup): Boolean {
        var isValid = true

        root.forEach {
            if (it is CustomEditText && !it.validateContent())
                isValid = false
        }

        return isValid
    }

    fun login(idNum: String, password: String): MutableLiveData<ApiResponse<User>> {
        isLoading.value = true

        return appRepositoryHelper.login(LoginRequest(idNum, password, userType.value!!.value)).apply {
            observeForever{
             handleResponseWhenError(it)
                if (it.isResponseSuccessful){
                    appRepositoryHelper.setUserId(it.responseBody!!.data.id)
                    appRepositoryHelper.setUserType(userType.value!!.value)
                }
            }
        }
    }
}