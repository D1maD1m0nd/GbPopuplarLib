package com.example.gbpopularlibs.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone

class App : Application() {
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    @SuppressLint("StaticFieldLeak")
    object ContextHolder { lateinit var context: Context }

    override fun onCreate() {
        super.onCreate()
        app = this
    }

    companion object {
        internal lateinit var app: App
            private set
    }
}