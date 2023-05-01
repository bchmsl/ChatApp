package com.bchmsl.chatapp.app

import android.app.Application
import com.bchmsl.chatapp.di.module.dbModule
import com.bchmsl.chatapp.di.module.repositoryModule
import com.bchmsl.chatapp.di.module.viewModelModule
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