package com.devopsolution.code9.common.extensions

import androidx.lifecycle.MutableLiveData
import com.devopsolution.code9.data.network.model.ApiResponse
import com.devopsolution.code9.data.network.model.GeneralResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<GeneralResponse<T>>.getResponse(): MutableLiveData<ApiResponse<T>> {

    val liveDataResponse = MutableLiveData<ApiResponse<T>>()
    val apiResponse = ApiResponse<T>()

    enqueue(object : Callback<GeneralResponse<T>> {

        override fun onFailure(call: Call<GeneralResponse<T>>, t: Throwable) {
            apiResponse.exception = t
            apiResponse.isCanceled = call.isCanceled

            liveDataResponse.postValue(apiResponse)
        }

        override fun onResponse(call: Call<GeneralResponse<T>>, response: Response<GeneralResponse<T>>) {
            apiResponse.responseCode = response.code()
            apiResponse.isResponseSuccessful = response.isSuccessful && response.body()?.isSuccess ?: false
            apiResponse.responseHeader = response.headers()

            if (response.isSuccessful)
                apiResponse.responseBody = response.body()
            else
                apiResponse.errorBody = response.errorBody()

            liveDataResponse.postValue(apiResponse)
        }
    })

    return liveDataResponse
}
