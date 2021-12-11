package com.example.gbpopularlibs.data.model.repository

import com.example.gbpopularlibs.data.model.User

interface Repository {
    fun generateUser(): List<User>
    fun getUser(login: String, password: String): User?
}