package com.example.gbpopularlibs.data.rest

import android.content.SharedPreferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object ApiUtils {
    const val BASE_URL = "https://api.github.com/"
    fun getHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
            val builder = request.method(original.method, original.body)
                .build()

            chain.proceed(builder)
        }
        return httpClient.build()
    }
}