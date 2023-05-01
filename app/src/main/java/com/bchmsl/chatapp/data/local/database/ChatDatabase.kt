package com.bchmsl.chatapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bchmsl.chatapp.data.local.dao.MessageDao
import com.bchmsl.chatapp.data.local.model.MessageEntity

@Database(entities = [MessageEntity::class], version = 1)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun dao(): MessageDao

    companion object {
        const val DATABASE_NAME = "chat_db"
    }
}