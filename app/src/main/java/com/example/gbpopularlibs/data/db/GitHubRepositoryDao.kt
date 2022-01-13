package com.example.gbpopularlibs.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gbpopularlibs.data.model.GitHubRepo
import com.example.gbpopularlibs.data.model.GitHubUser
import io.reactivex.rxjava3.core.Single

@Dao
interface GitHubRepositoryDao {
    @Query("SELECT * FROM GitHubRepoTable")
    fun getAllRepos() : Single<List<GitHubRepo>>

    @Query("SELECT * FROM GitHubRepoTable where ownerLogin = :login")
    fun getReposByUserLogin(login : String) : Single<List<GitHubRepo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<GitHubRepo>)
}