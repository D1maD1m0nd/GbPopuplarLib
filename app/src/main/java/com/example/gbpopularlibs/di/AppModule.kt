package com.example.gbpopularlibs.di

import com.example.gbpopularlibs.app.App
import com.example.gbpopularlibs.di.components.ProfileComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [ProfileComponent::class])
class AppModule(var app: App) {

    @Provides
    @Singleton
    fun app(): App {
        return app
    }

}