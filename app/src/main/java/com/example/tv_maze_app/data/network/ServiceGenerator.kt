package com.example.tv_maze_app.data.network

import android.os.Build
import com.example.tv_maze_app.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object ServiceGenerator {

    private val httpClient: OkHttpClient.Builder
    private val builder: Retrofit.Builder

    init {

        Timber.d("[API BASE URL] %s", BuildConfig.API_BASE_URL)

        val apiBaseUrl = BuildConfig.API_BASE_URL

        httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("DEVICE-OS", "ANDROID")
                .addHeader("DEVICE-OS-VERSION", Build.VERSION.RELEASE)
                .addHeader("DEVICE-GENERAL-INFO", Build.MODEL)
                .build()
            chain.proceed(request)
        }

        builder = Retrofit.Builder()
            .baseUrl(apiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
    }

    fun <S> createService(serviceClass: Class<S>): S {
        Timber.d("Call <%s>", serviceClass.simpleName)
        val client = httpClient
            .build()
        val retrofit = builder
            .client(client)
            .build()
        return retrofit.create(serviceClass)
    }
}