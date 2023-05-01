package com.bchmsl.chatapp.di.module

import android.content.Context
import androidx.room.Room
import com.bchmsl.chatapp.data.local.dao.MessageDao
import com.bchmsl.chatapp.data.local.database.ChatDatabase
import org.koin.dsl.module

val dbModule = module {
    single { provideChatDatabase(get()) }
    single { provideDao(get()) }
}

private fun provideChatDatabase(context: Context): ChatDatabase =
    Room.databaseBuilder(
        context,
        ChatDatabase::class.java,
        ChatDatabase.DATABASE_NAME
    ).build()

private fun provideDao(database: ChatDatabase): MessageDao =
    database.dao()
