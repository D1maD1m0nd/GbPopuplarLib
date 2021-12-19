package com.example.gbpopularlibs.data.model.repository

import com.example.gbpopularlibs.data.model.User
import com.example.gbpopularlibs.data.model.UserRepository
import io.reactivex.rxjava3.core.Observable

interface Repository {
    fun getUsers(): Observable<List<User>>
    fun getRepositories() : Observable<List<UserRepository>>
    fun getUser(login: String, password: String): Observable<User?>
}