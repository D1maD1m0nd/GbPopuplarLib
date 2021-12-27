package com.example.gbpopularlibs.data.model.repository

import com.example.gbpopularlibs.data.db.RoomFactory
import com.example.gbpopularlibs.data.model.GitHubUser
import com.example.gbpopularlibs.data.rest.GitHubApiImpl
import io.reactivex.rxjava3.core.Single

class GitHubUserRepositoryImpl : GitHubUserRepository {
    var apiService = GitHubApiImpl.getApiService()
    private val roomDb = RoomFactory.create().userDao()
    override fun getGitHubUser(login: String) : Single<GitHubUser>{
        return roomDb.getUserByLogin(login).flatMap {
            Single.just(it)
        }
    }

    override fun getGitHubUsers(): Single<List<GitHubUser>> {
        return roomDb.getAllUsers()
            .flatMap {
                if (it.isEmpty()) {
                    apiService.getUsers()
                        .map { resultFromServer ->
                            roomDb.insertAll(resultFromServer)
                            resultFromServer
                        }
                } else {
                    Single.just(it)
                }
            }
    }

}