package com.example.gbpopularlibs.data.model.repository

import com.example.gbpopularlibs.data.db.DbStorage
import com.example.gbpopularlibs.data.model.GitHubUser
import com.example.gbpopularlibs.data.rest.GitHubApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GitHubUserRepositoryImpl
@Inject constructor(
    private val apiService: GitHubApiService,
    private val roomDb: DbStorage
) : GitHubUserRepository {
    private val dao = roomDb.userDao()
    override fun getGitHubUser(login: String) : Single<GitHubUser>{

        return dao.getUserByLogin(login).flatMap {
            Single.just(it)
        }
    }

    override fun getGitHubUsers(): Single<List<GitHubUser>> {
        return dao.getAllUsers()
            .flatMap {
                if (it.isEmpty()) {
                    apiService.getUsers()
                        .map { resultFromServer ->
                            dao.insertAll(resultFromServer)
                            resultFromServer
                        }
                } else {
                    Single.just(it)
                }
            }
    }

}