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
    fun gitRepoDao(cache: DbStorage) : GitHubRepositoryDao = cache.repoDao()

    @FragmentScope
    @Provides
    fun gitRepoRepository(api: GitHubApiService, dao: GitHubRepositoryDao)
    : GitHubRepoRepository = GitHubRepoRepositoryImpl(api, dao)
}