package com.example.gbpopularlibs.app

import android.app.Application
import com.example.gbpopularlibs.di.AppModule
import com.example.gbpopularlibs.di.ApplicationComponent
import com.example.gbpopularlibs.di.DaggerApplicationComponent

class App : Application() {
    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        app = this
        appComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()

    }

    companion object {
        internal lateinit var app: App
            private set
    }
}