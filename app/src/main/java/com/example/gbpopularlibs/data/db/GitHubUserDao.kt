package com.example.gbpopularlibs.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gbpopularlibs.data.model.GitHubUser
import io.reactivex.rxjava3.core.Single

@Dao
interface GitHubUserDao {
    @Query("SELECT * FROM GitHubUserTable")
    fun getAllUsers() : Single<List<GitHubUser>>

    @Query("SELECT * FROM GitHubUserTable where login = :login")
    fun getUserByLogin(login : String) : Single<GitHubUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<GitHubUser>)
}