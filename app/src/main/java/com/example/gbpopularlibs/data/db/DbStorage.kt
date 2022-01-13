package com.example.gbpopularlibs.data.db


import androidx.room.AutoMigration
import androidx.room.RoomDatabase
import com.example.gbpopularlibs.data.model.GitHubRepo
import com.example.gbpopularlibs.data.model.GitHubUser

@androidx.room.Database(
    entities = [
        GitHubUser::class,
        GitHubRepo :: class
    ],
    version = 4,
    exportSchema = true,
    autoMigrations = [
        AutoMigration (from = 3, to = 4)
    ]
)
abstract class DbStorage : RoomDatabase() {
    abstract fun userDao(): GitHubUserDao
    abstract fun repoDao() : GitHubRepositoryDao
    companion object {
        const val DB_NAME = "github_user.db"
    }
}