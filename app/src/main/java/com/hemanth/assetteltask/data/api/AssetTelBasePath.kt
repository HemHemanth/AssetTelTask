package com.hemanth.assetteltask.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AssetTelBasePath {
    fun getAssetTelServices(): EndPoints = Retrofit.Builder()
        .baseUrl("http://34.70.239.163")
        .addConverterFactory(GsonConverterFactory.create())
        .client(getLogClient())
        .build()
        .create(EndPoints::class.java)

    private fun getLogClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(interceptor)
        client.retryOnConnectionFailure(false)
        client.connectTimeout(150, TimeUnit.SECONDS).readTimeout(150, TimeUnit.SECONDS)
        return client.build()
    }
}