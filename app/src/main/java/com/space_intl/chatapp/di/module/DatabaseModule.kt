package com.space_intl.chatapp.di.module

import android.content.Context
import androidx.room.Room
import com.space_intl.chatapp.data.local.dao.MessageDao
import com.space_intl.chatapp.data.local.dao.UserDao
import com.space_intl.chatapp.data.local.database.ChatDatabase
import org.koin.dsl.module

/**
 * Module for the database.
 * @see module
 */
val dbModule = module {
    single { provideChatDatabase(get()) }
    single { provideChatDao(get()) }
    single { provideUserDao(get()) }
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
 * Provides the Chat DAO.
 *
 * @param database the database.
 * @return the Chat DAO
 */
private fun provideChatDao(database: ChatDatabase): MessageDao =
    database.messageDao()

/**
 * Provides the User DAO.
 *
 * @param database the database.
 * @return the User DAO
 */
private fun provideUserDao(database: ChatDatabase): UserDao =
    database.userDao()
