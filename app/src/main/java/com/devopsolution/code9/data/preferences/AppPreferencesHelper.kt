package com.devopsolution.code9.data.preferences

import android.content.SharedPreferences
import com.devopsolution.code9.common.Constants
import com.devopsolution.code9.common.extensions.get
import com.devopsolution.code9.common.extensions.put
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferencesHelper @Inject constructor(private val pref : SharedPreferences) : PreferencesHelper {

    override fun setUserId(id : String?) = pref.put(Constants.USER_ID, id)
    override fun getUserId() = pref.get<String>(Constants.USER_ID, null)

    override fun setUserType(type : Int) = pref.put(Constants.USER_TYPE, type)
    override fun getUserType() = pref.get(Constants.USER_TYPE, 0)

    override fun setFcmToken(token : String) = pref.put(Constants.USER_FCM_TOKEN, token)
    override fun getFcmToken() = pref.get<String>(Constants.USER_FCM_TOKEN, null)
}