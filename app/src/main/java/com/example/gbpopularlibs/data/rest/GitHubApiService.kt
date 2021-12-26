package com.example.gbpopularlibs.data.rest

import com.example.gbpopularlibs.data.model.GitHubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiService {

    @GET("/users/{login}")
    fun getUserByLogin(@Path("login") login: String): Single<GitHubUser>

    @GET("users")
    fun getUsers() : Single<List<GitHubUser>>
}