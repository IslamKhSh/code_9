package com.devopsolution.code9.data.network

import androidx.lifecycle.MutableLiveData
import com.devopsolution.code9.data.network.model.*

interface NetworkHelper {
    fun login(loginRequest: LoginRequest): MutableLiveData<ApiResponse<User>>
    fun getDashBoard(dashboardRequest: DashboardRequest? = null): MutableLiveData<ApiResponse<Dashboard>>
    fun getCategories(id: String? = null): MutableLiveData<ApiResponse<List<Category>>>
    fun getUserInfo(id: String): MutableLiveData<ApiResponse<User>>
    fun checkInUser(userId: String, shopId: String? = null): MutableLiveData<ApiResponse<Any?>>

    fun registerToken(
        fcmToken: String,
        deviceId: String,
        userId: String? = null
    ): MutableLiveData<ApiResponse<Any?>>

    fun checkOutUser(userId: String, shopId: String? = null): MutableLiveData<ApiResponse<Any?>>
}