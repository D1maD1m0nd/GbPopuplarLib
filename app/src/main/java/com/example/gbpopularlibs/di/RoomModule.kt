package com.example.gbpopularlibs.di

import androidx.room.Room
import com.example.gbpopularlibs.app.App
import com.example.gbpopularlibs.data.db.DbStorage
import com.example.gbpopularlibs.data.db.DbStorage.Companion.DB_NAME
import com.example.gbpopularlibs.di.components.ProfileComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class RoomModule {
    @Singleton
    @Provides
    fun database(app: App): DbStorage = Room.databaseBuilder(app, DbStorage::class.java, DB_NAME)
        .build()
}