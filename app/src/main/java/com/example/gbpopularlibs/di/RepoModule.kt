package com.example.gbpopularlibs.di

import com.example.gbpopularlibs.data.db.DbStorage
import com.example.gbpopularlibs.data.model.repository.GitHubUserRepository
import com.example.gbpopularlibs.data.model.repository.GitHubUserRepositoryImpl
import com.example.gbpopularlibs.data.model.repository.Repository
import com.example.gbpopularlibs.data.model.repository.RepositoryImpl
import com.example.gbpopularlibs.data.rest.GitHubApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(api: GitHubApiService, cache: DbStorage):
            GitHubUserRepository = GitHubUserRepositoryImpl(api, cache)

    @Singleton
    @Provides
    fun authRepository() : Repository = RepositoryImpl()
}