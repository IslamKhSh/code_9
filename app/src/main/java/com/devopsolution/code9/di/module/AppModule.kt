package com.devopsolution.code9.di.module

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.devopsolution.code9.Code9App
import com.devopsolution.code9.common.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Code9App) {

    /**
     * Provides App instance of the application
     *
     * @return the instance of application
     */
    @Provides
    @Singleton
    fun provideApplication() = application

    /**
     * provide context to be used in data repository
     *
     * @return applicationContext
     */
    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

    /**
     * provide shared preference to store data
     *
     * @return shared preference instance
     */
    @Provides
    @Singleton
    fun provideSharedPref() = application.getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE)
}