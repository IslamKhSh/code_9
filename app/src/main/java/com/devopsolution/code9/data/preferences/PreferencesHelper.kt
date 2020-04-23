package com.devopsolution.code9.data.preferences

import com.devopsolution.code9.data.network.model.LoginRequest

interface PreferencesHelper {

    fun getUserId(): String?
    fun getUserType(): Int?
    fun setUserId(id: String?)
    fun setUserType(type: Int)
    fun setFcmToken(token: String)
    fun getFcmToken(): String?
}