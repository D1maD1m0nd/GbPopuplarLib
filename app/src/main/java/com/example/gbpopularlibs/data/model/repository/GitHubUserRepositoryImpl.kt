package com.example.gbpopularlibs.data.model.repository

import com.example.gbpopularlibs.data.model.GitHubUser
import com.example.gbpopularlibs.data.rest.GitHubApiImpl
import io.reactivex.rxjava3.core.Single

class GitHubUserRepositoryImpl : GitHubUserRepository {
    var apiService = GitHubApiImpl.getApiService()

    override fun getGitHubUser(login: String) = apiService.getUserByLogin(login)

}