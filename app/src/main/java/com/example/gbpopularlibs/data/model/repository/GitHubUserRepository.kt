package com.example.gbpopularlibs.data.model.repository

import com.example.gbpopularlibs.data.model.GitHubUser
import io.reactivex.rxjava3.core.Single

interface GitHubUserRepository {
    fun getGitHubUser(login : String) : Single<GitHubUser>
}