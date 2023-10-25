package com.example.alfagift.api

import com.example.alfagift.BuildConfig
import com.example.alfagift.commons.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ConnectionBuilder {
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(Constant.Connection.connect_timeout, TimeUnit.SECONDS)
        .readTimeout(Constant.Connection.read_timeout, TimeUnit.SECONDS)
        .writeTimeout(Constant.Connection.write_timeout, TimeUnit.SECONDS)
        .build()

    val connectionService: ConnectionService = Retrofit.Builder()
        .baseUrl(BuildConfig.baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ConnectionService::class.java)
}