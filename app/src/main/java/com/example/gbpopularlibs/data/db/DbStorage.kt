package com.example.gbpopularlibs.data.db

import androidx.room.AutoMigration
import androidx.room.RoomDatabase
import com.example.gbpopularlibs.data.model.GitHubUser

@androidx.room.Database(
    entities = [
        GitHubUser::class
    ],
    version = 2,
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ],
    exportSchema = true
)
abstract class DbStorage : RoomDatabase() {
    abstract fun userDao(): GitHubUserDao
    companion object {
        const val DB_NAME = "github.db"
    }
}