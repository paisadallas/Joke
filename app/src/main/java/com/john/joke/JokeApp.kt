package com.john.joke

import android.app.Application
import com.john.joke.di.applicationModule
import com.john.joke.di.networkModule
import com.john.joke.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JokeApp:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@JokeApp)
            modules(listOf(networkModule, applicationModule, viewModelModule))
        }
    }
}