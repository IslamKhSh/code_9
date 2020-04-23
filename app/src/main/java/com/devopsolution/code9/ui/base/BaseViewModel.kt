package com.devopsolution.code9.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.devopsolution.code9.Code9App
import com.devopsolution.code9.R
import com.devopsolution.code9.data.AppRepositoryHelper
import com.devopsolution.code9.data.network.model.ApiResponse
import com.devopsolution.code9.data.network.model.ErrorResponse
import com.devopsolution.code9.data.network.model.GeneralResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
abstract class BaseViewModel(val app: Application) : AndroidViewModel(app) {

    @Inject
    lateinit var appRepositoryHelper: AppRepositoryHelper

    init {
        (app as? Code9App)?.component?.inject(this)
    }

    /**
     * label to be set as toolbar title
     */
    val navDestinationLabel = MutableLiveData("")

    val storedUserType = MutableLiveData(appRepositoryHelper.getUserType())
    val storedUserId = appRepositoryHelper.getUserId()

    val isUpBtnClicked = MutableLiveData(false)

    /** error responses to observe on it and display error**/
    var errorMsg = MutableLiveData<String>()
    var errorMsgRes = MutableLiveData<Int>()

    val isLogoutRequired = MutableLiveData(false)

    /** livData of boolean value used to handle displaying and hiding of progressbar **/
    val isLoading = MutableLiveData<Boolean>().apply { value = false }

    /**
     * MediatorLiveData, all requests will be added as source to it to handle any error or failure.
     **/
    val errorResponse = MediatorLiveData<ApiResponse<*>>()


    fun onUpBtnClicked() {
        isUpBtnClicked.value = true
    }

    fun <T> handleResponseWhenError(response: ApiResponse<T>) {
        isLoading.value = false
        if (response.isResponseSuccessful || response.isCanceled) return
        if (response.exception != null) {
            if (response.exception is IOException) errorMsgRes.postValue(R.string.msg_error_connection) else errorMsgRes.postValue(
                R.string.msg_something_error
            )
        } else when (response.responseCode) {
            200 -> {

                if (!response.responseBody?.errors.isNullOrEmpty())
                    errorMsg.postValue(response.responseBody!!.errors[0])
                else
                    errorMsgRes.postValue(R.string.msg_something_error)
            }
            504 -> errorMsgRes.postValue(R.string.no_internet)
            500 -> errorMsgRes.postValue(R.string.msg_server_error)
            401 -> isLogoutRequired.postValue(true)
            else -> errorMsgRes.postValue(R.string.msg_something_error)
        }
    }


}
