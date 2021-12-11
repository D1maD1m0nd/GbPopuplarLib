package com.example.gbpopularlibs.app

import android.app.Application
import com.github.terrakok.cicerone.Cicerone

class App : Application() {
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()
        app = this
    }

    companion object {
        internal lateinit var app: App
            private set
    }
}