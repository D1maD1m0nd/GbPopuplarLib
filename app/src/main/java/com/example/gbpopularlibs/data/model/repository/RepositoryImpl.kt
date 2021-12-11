package com.example.gbpopularlibs.data.model.repository

import com.example.gbpopularlibs.data.model.User
import java.util.*
import kotlin.collections.ArrayList

class RepositoryImpl : Repository {
    private val users = mutableListOf(User("admin", "admin"), User("root", "root"))

    init {
        users.addAll(generateUser())
    }

    override fun generateUser(): List<User> {
        val users = ArrayList<User>(DEFAULT_SIZE_USERS_LIST)
        for (i in range) {
            val user = User("User$i", UUID.randomUUID().toString())
            users.add(user)
        }
        return users
    }

    override fun getUser(login: String, password: String): User? {
        return users.findLast { login == it.login && password == it.password }
    }

    private companion object {
        val range = 0..100000
        const val DEFAULT_SIZE_USERS_LIST = 10000
    }
}