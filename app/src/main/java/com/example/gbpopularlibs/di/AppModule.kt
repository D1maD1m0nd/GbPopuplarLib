package com.example.gbpopularlibs.di

import com.example.gbpopularlibs.app.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var app: App) {

    @Provides
    @Singleton
    fun app(): App {
        return app
    }

}