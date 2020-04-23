package com.devopsolution.code9.data.network

import com.devopsolution.code9.data.network.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("Account/Login")
    fun login(@Body loginRequest: LoginRequest): Call<GeneralResponse<User>>

    @POST("Dashbord/GetDashboardData")
    fun getDashBoard(@Body dashboardRequest: DashboardRequest): Call<GeneralResponse<Dashboard>>

    @POST("Citizen/GetCategories")
    fun getCategories(@Query("id") id: String): Call<GeneralResponse<List<Category>>>

    @POST("Shop/GetUserInfo")
    fun getUserInfo(@Query("id") id: String): Call<GeneralResponse<User>>

    @POST("Shop/CheckIn")
    fun checkInUser(@Body checkInRequest: CheckInRequest): Call<GeneralResponse<Any?>>

    @POST("Shop/Checkout")
    fun checkOutUser(@Body checkInRequest: CheckInRequest): Call<GeneralResponse<Any?>>

    @POST("Admin/RegisterDeviceToken")
    fun registerToken(@Body body: Map<String, String>) : Call<GeneralResponse<Any?>>

    @POST("Admin/UnRegisterDeviceToken")
    fun logout(@Query("userId") userId: String): Call<GeneralResponse<Any?>>


}