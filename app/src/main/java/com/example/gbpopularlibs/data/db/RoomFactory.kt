package com.example.gbpopularlibs.data.db

import androidx.room.Room
import com.example.gbpopularlibs.app.App

object RoomFactory {
    private const val DB_NAME = "github.db"
    private val database: DbStorage by lazy {
        Room.databaseBuilder(App.app.applicationContext, DbStorage::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    fun create(): DbStorage = database
}