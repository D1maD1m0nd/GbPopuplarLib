package com.example.gbpopularlibs.di.components

import com.example.gbpopularlibs.data.db.DbStorage
import com.example.gbpopularlibs.data.db.GitHubRepositoryDao
import com.example.gbpopularlibs.data.model.repository.GitHubRepoRepository
import com.example.gbpopularlibs.data.model.repository.GitHubRepoRepositoryImpl
import com.example.gbpopularlibs.data.rest.GitHubApiService
import dagger.Module
import dagger.Provides


@Module
class ProfileRepoModule {
    @FragmentScope
    @Provides
    fun gitRepoRepository(api: GitHubApiService, cache: DbStorage)
    : GitHubRepoRepository = GitHubRepoRepositoryImpl(api, cache)
}