package com.devopsolution.code9.data.network

import com.devopsolution.code9.common.extensions.getResponse
import com.devopsolution.code9.data.network.model.CheckInRequest
import com.devopsolution.code9.data.network.model.DashboardRequest
import com.devopsolution.code9.data.network.model.LoginRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNetworkHelper @Inject constructor(private val apiService: ApiService) : NetworkHelper {

    override fun login(loginRequest: LoginRequest) = apiService.login(loginRequest).getResponse()

    override fun getDashBoard(dashboardRequest: DashboardRequest?) =
        apiService.getDashBoard(dashboardRequest!!).getResponse()

    override fun getCategories(id: String?) = apiService.getCategories(id!!).getResponse()

    override fun getUserInfo(id: String) = apiService.getUserInfo(id).getResponse()

    override fun checkInUser(userId: String, shopId: String?) =
        apiService.checkInUser(CheckInRequest(shopId!!, userId)).getResponse()

    override fun registerToken(fcmToken: String, deviceId: String, userId: String?) =
        apiService.registerToken(
            mapOf(
                "newToken" to fcmToken,
                "deviceName" to deviceId,
                "userId" to userId!!
            )
        ).getResponse()
}