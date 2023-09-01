package com.space_intl.chatapp.app

import android.app.Application
import com.space_intl.chatapp.di.module.dbModule
import com.space_intl.chatapp.di.module.repositoryModule
import com.space_intl.chatapp.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Android application class to implement Koin dependency injection.
 * @see <a href="https://insert-koin.io/docs/quickstart/android/">Koin Android Quickstart</a>
 */

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
