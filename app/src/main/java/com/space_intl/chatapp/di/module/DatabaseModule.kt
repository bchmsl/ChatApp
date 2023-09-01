package com.space_intl.chatapp.di.module

import android.content.Context
import androidx.room.Room
import com.space_intl.chatapp.data.local.dao.MessageDao
import com.space_intl.chatapp.data.local.database.ChatDatabase
import org.koin.dsl.module

/**
 * Module for the database.
 * @see module
 */
val dbModule = module {
    single { provideChatDatabase(get()) }
    single { provideDao(get()) }
}

/**
 * Provides the database.
 *
 * @param context the application context.
 * @return the database.
 *
 * @see Room
 */
private fun provideChatDatabase(context: Context): ChatDatabase =
    Room.databaseBuilder(
        context,
        ChatDatabase::class.java,
        ChatDatabase.DATABASE_NAME
    ).build()

/**
 * Provides the DAO.
 *
 * @param database the database.
 * @return the DAO.
 */
private fun provideDao(database: ChatDatabase): MessageDao =
    database.dao()
