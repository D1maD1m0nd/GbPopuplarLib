package com.example.gbpopularlibs.data.rest

import android.content.SharedPreferences
import com.example.gbpopularlibs.data.model.GitHubUser
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody.Part.Companion.create
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
object GitHubApiImpl {
    private val api: GitHubApiService by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(ApiUtils.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(ApiUtils.getHttpClient())
            .build()

        adapter.create(GitHubApiService::class.java)
    }

    fun getApiService() = api
}