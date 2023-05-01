package com.bchmsl.chatapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bchmsl.chatapp.data.local.model.MessageEntity

@Dao
interface MessageDao {
    @Query("SELECT * FROM chat")
    fun retrieveAll(): List<MessageEntity>

    @Insert
    fun insertMessage(messageEntity: MessageEntity)
}