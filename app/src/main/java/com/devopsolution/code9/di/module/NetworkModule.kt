package com.devopsolution.code9.di.module

import android.content.Context
import com.devopsolution.code9.BuildConfig
import com.devopsolution.code9.common.Constants
import com.devopsolution.code9.common.utils.NetworkUtils
import com.devopsolution.code9.data.network.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


/**
 * Module which provides all required dependencies about network
 */
@Module
class NetworkModule {

    /**
     * provide Http Interceptor to be used in logging networking
     *
     * @return an instance of Http Interceptor with custom logging
     */
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    /**
     * Provides okHttp cache to be used in okHttp client
     *
     * @return the okHttp cache
     */
    @Provides
    @Singleton
    fun provideHttpCache(context: Context) = Cache(context.filesDir, Constants.CACHE_SIZE.toLong())

    /**
     * Provide Http Interceptor to update cache if network connected and add Cache-Control to responseBody
     * header if Cache not supported in the server
     *
     * @param context the application context to e used in check the network state
     * @return  Http Interceptor instance
     */
    @Provides
    @Singleton
    @Named("online_interceptor")
    fun provideHttpOnlineInterceptor(context: Context): Interceptor {

        return Interceptor { chain ->
            val response = chain.proceed(chain.request())

            val headers = response.header("Cache-Control")
            if (NetworkUtils.isNetworkConnected(context)!! && (headers == null ||
                        headers.contains("no-store") || headers.contains("must-revalidate") ||
                        headers.contains("no-cache") || headers.contains("max-age=0"))
            ) {

                response.newBuilder()
                    .header("Cache-Control", "public, max-age=${Constants.CACHE_MAX_AGE}")
                    .build()
            } else
                response
        }
    }

    /**
     * Provide Http Interceptor to get data from cache if network disconnected
     *
     * @param context the application context to e used in check the network state
     * @return  Http Interceptor instance
     */
    @Provides
    @Singleton
    @Named("offline_interceptor")
    fun provideHttpOfflineInterceptor(context: Context): Interceptor {

        return Interceptor { chain ->
            var request = chain.request()
            if (!NetworkUtils.isNetworkConnected(context)!!) {
                request = request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=${Constants.CACHE_MAX_STALE}"
                )
                    .build()
            }
            chain.proceed(request)
        }
    }


    /**
     * Provides the okHttp client for networking
     *
     * @param cache the okHttp cache
     * @param loggingInterceptor the okHttp logging interceptor
     * @param onlineInterceptor the interceptor to be used in case of online network
     * @param offlineInterceptor the interceptor to be used in case of offline network
     * @return okHttp client instance
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        cache: Cache, loggingInterceptor: HttpLoggingInterceptor,
        @Named("online_interceptor") onlineInterceptor: Interceptor,
        @Named("offline_interceptor") offlineInterceptor: Interceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.cache(cache)
            .addNetworkInterceptor(onlineInterceptor)
            .addInterceptor(offlineInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(300, TimeUnit.SECONDS) // connect timeout
            .readTimeout(300, TimeUnit.SECONDS)    // socket timeout


        return client.build()
    }


    /**
     * Provides the Retrofit object.
     *
     * @param httpClient the okHttp client
     * @return the Retrofit object
     */
    @Provides
    @Singleton
    fun provideRetrofitInterface(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    /**
     * Provides the service implementation.
     *
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the ApiService implementation.
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
}