package com.devopsolution.code9.data

import androidx.lifecycle.MutableLiveData
import com.devopsolution.code9.data.network.AppNetworkHelper
import com.devopsolution.code9.data.network.model.*
import com.devopsolution.code9.data.preferences.AppPreferencesHelper
import com.devopsolution.code9.data.preferences.PreferencesHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryHelper @Inject constructor(
    private val networkHelper: AppNetworkHelper,
    private val prefHelper: AppPreferencesHelper
) : RepositoryHelper, PreferencesHelper {

    override fun login(loginRequest: LoginRequest) = networkHelper.login(loginRequest)

    override fun getDashBoard(dashboardRequest: DashboardRequest?) =
        networkHelper.getDashBoard(DashboardRequest(getUserId(), getUserType()))

    override fun getCategories(id: String?) = networkHelper.getCategories(getUserId())

    override fun getUserInfo(id: String) = networkHelper.getUserInfo(id)

    override fun checkInUser(userId: String, shopId: String?) =
        networkHelper.checkInUser(userId, getUserId())

    override fun checkOutUser(userId: String, shopId: String?) =
        networkHelper.checkOutUser(userId, getUserId())

    override fun registerToken(
        fcmToken: String,
        deviceId: String,
        userId: String?
    ) = networkHelper.registerToken(fcmToken, deviceId, getUserId())

    override fun logout(userId: String?) = networkHelper.logout(getUserId())

    ///////// preferences /////////////
    override fun setUserId(id: String?) = prefHelper.setUserId(id)
    override fun getUserId() = prefHelper.getUserId()

    override fun setUserType(type: Int) = prefHelper.setUserType(type)
    override fun getUserType() = prefHelper.getUserType()

    override fun setFcmToken(token: String) = prefHelper.setFcmToken(token)
    override fun getFcmToken() = prefHelper.getFcmToken()

    override fun clearUserData() = prefHelper.clearUserData()
}