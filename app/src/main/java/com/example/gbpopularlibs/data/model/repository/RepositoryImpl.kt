package com.example.gbpopularlibs.data.model.repository

import com.example.gbpopularlibs.data.model.User
import com.example.gbpopularlibs.data.model.UserRepository
import java.util.*
import kotlin.collections.ArrayList
import io.reactivex.rxjava3.core.Observable

class RepositoryImpl : Repository {
    private val users = mutableListOf(User("admin", "admin"), User("root", "root"))

    init {
        val obsUsers = getUsers()
        val obsRepository = getRepositories()
        Observable
            .zip(obsUsers, obsRepository, { obs1, obs2 -> Pair(obs1, obs2) })
            .map {
                val first = it.first
                val second = it.second
                first.map { user ->
                    val repo = second.findLast { repo -> user.login == repo.userLogin  }
                    user.repo = repo
                    user
                }
            }
            .subscribe {users.addAll(it)}
    }

    override fun getUsers(): Observable<List<User>> {
        val users = ArrayList<User>(DEFAULT_SIZE_USERS_LIST)
        for (i in range) {
            val user = User("User$i", UUID.randomUUID().toString())
            users.add(user)
        }
        return Observable.just(users)
    }

    override fun getRepositories(): Observable<List<UserRepository>> {
        val repos = ArrayList<UserRepository>(DEFAULT_SIZE_USERS_LIST / 2)
        for (i in range) {
            val repo = UserRepository("User$i")
            repos.add(repo)
        }
        return Observable.just(repos)
    }

    override fun getUser(login: String, password: String): Observable<User?> {
        var user = users.findLast { login == it.login && password == it.password }
        return Observable.just(user)
    }

    private companion object {
        val range = 0..100000
        const val DEFAULT_SIZE_USERS_LIST = 10000
    }
}