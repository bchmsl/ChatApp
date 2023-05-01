package com.bchmsl.chatapp.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bchmsl.chatapp.data.local.dao.MessageDao
import com.bchmsl.chatapp.data.local.database.ChatDatabase
import org.koin.dsl.module

fun provideChatDatabase(context: Context): RoomDatabase =
    Room.databaseBuilder(
        context,
        ChatDatabase::class.java,
        ChatDatabase.DATABASE_NAME
    ).build()

fun provideDao(database: ChatDatabase): MessageDao =
    database.dao()

val dbModule = module {
    single { provideChatDatabase(get()) }
    single { provideDao(get()) }
}