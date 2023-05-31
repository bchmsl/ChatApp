package com.space_intl.chatapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space_intl.chatapp.data.local.dao.MessageDao
import com.space_intl.chatapp.data.local.dao.UserDao
import com.space_intl.chatapp.data.local.model.MessageEntity
import com.space_intl.chatapp.data.local.model.UserEntity

/**
 * Database class for the chat.
 * @see Database
 */
@Database(entities = [MessageEntity::class, UserEntity::class], version = 1)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "chat_db"
    }
}
