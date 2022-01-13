package com.example.gbpopularlibs.data.model.repository

import com.example.gbpopularlibs.data.db.DbStorage
import com.example.gbpopularlibs.data.model.GitHubRepo
import com.example.gbpopularlibs.data.rest.GitHubApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GitHubRepoRepositoryImpl
    @Inject constructor(
        private val apiService: GitHubApiService,
        roomDb: DbStorage,
    ): GitHubRepoRepository {
    private val dao = roomDb.repoDao()
    override fun getUserRepo(login: String) : Single<List<GitHubRepo>>{
        return dao.getReposByUserLogin(login)
            .flatMap {
                if(it.isEmpty()) {
                    apiService.getUserRepositories(login)
                        .map { repos ->
                            val newRepo = repos.map {
                                it.ownerLogin = login
                                it
                            }
                            dao.insertAll(newRepo)
                            repos
                        }
                } else {
                    Single.just(it)
                }
            }
    }
}