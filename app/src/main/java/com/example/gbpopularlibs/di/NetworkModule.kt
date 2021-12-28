package com.example.gbpopularlibs.di

import com.example.gbpopularlibs.data.rest.ApiUtils
import com.example.gbpopularlibs.data.rest.GitHubApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun apiClient() : OkHttpClient = ApiUtils().getHttpClient()

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://api.github.com/"

    @Singleton
    @Provides
    fun moshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Reusable
    fun api(@Named("baseUrl") baseUrl: String, apiClient: OkHttpClient, moshi : Moshi): GitHubApiService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(apiClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(GitHubApiService::class.java)


}