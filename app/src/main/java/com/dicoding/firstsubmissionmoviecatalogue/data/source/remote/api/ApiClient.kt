package com.dicoding.firstsubmissionmoviecatalogue.data.source.remote.api

import com.dicoding.firstsubmissionmoviecatalogue.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder().apply {
            client(httpClient)
            baseUrl(BuildConfig.BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
        }
    }

    val instance: ApiServices by lazy {
        retrofit.build().create(ApiServices::class.java)
    }
}