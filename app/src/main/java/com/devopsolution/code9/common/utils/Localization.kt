package com.devopsolution.code9.common.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.annotation.IntDef
import com.devopsolution.code9.Code9App
import com.devopsolution.code9.common.Constants.USER_LANGUAGE
import com.devopsolution.code9.di.component.DaggerAppComponent
import com.devopsolution.code9.di.module.AppModule
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.util.*

object Localization {

    fun setLanguage(activity: Activity, targetLanguage: Int) {
        val preferences = (activity.application as Code9App)
            .component
            .getSharedPreference()
        preferences.edit().putInt(USER_LANGUAGE, targetLanguage).apply()
    }

    fun updateLanguage(activity: Activity) {
        val preferences = (activity.application as Code9App)
            .component
            .getSharedPreference()

        var lang = preferences.getInt(
            USER_LANGUAGE,
            Language.ENGLISH.value /* change it according app default lang */
        )
        if (lang == Language.DEVICE_LANG.value) lang =
            currentLang
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            updateResources(
                activity,
                if (lang == Language.ARABIC.value) "ar" else "en"
            )
            updateResources(
                activity.applicationContext,
                if (lang == Language.ARABIC.value) "ar" else "en"
            )
        } else {
            updateResourcesLegacy(
                activity,
                if (lang == Language.ARABIC.value) "ar" else "en"
            )
        }
        preferences.edit().putInt(USER_LANGUAGE, lang).apply()
    }

    val currentLang: Int
        get() = if (
            Locale.getDefault().language.toLowerCase().contains("ar")
        ) Language.ARABIC.value else Language.ENGLISH.value

    val currentLangString: String
        get() = if (
            Locale.getDefault().language.toLowerCase().contains("ar")
        ) "ar" else "en"


    fun getStoredLanguage(activity: Activity) = (activity.application as Code9App)
        .component
        .getSharedPreference()
        .getInt(USER_LANGUAGE, Language.DEVICE_LANG.value)


    private fun updateResources(context: Context, language: String) {
        val locale = Locale(language)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
    }

    private fun updateResourcesLegacy(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale)
        }
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    enum class Language(val value: Int) {
        DEVICE_LANG(-1),
        ENGLISH(0),
        ARABIC(1)
    }
}