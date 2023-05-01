package com.space_intl.chatapp.app

import android.app.Application
import com.space_intl.chatapp.di.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                dbModule,
                repositoryModule,
                viewModelModule,
            )
        }
    }
}