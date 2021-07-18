package com.dani.movie

import android.app.Application
import org.koin.core.context.loadKoinModules

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoinModules(

        )
    }
}