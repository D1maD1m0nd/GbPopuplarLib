package com.example.gbpopularlibs.data.model.repository

import com.example.gbpopularlibs.data.model.GitHubRepo
import io.reactivex.rxjava3.core.Single

interface GitHubRepoRepository {
    fun getUserRepo(login : String): Single<List<GitHubRepo>>
}