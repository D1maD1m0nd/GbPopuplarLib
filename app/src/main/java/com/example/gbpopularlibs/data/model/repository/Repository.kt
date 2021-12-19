package com.example.gbpopularlibs.data.model.repository

import com.example.gbpopularlibs.data.model.User
import io.reactivex.rxjava3.core.Observable

interface Repository {
    fun generateUser(): List<User>
    fun getUser(login: String, password: String): Observable<User?>
}