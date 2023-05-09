package com.space_intl.chatapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.space_intl.chatapp.data.local.model.MessageEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data access object for [MessageEntity] model.
 * @see Dao
 */
@Dao
interface MessageDao {
    @Query("SELECT * FROM chat")
    fun retrieveAll(): Flow<List<MessageEntity>>

    @Insert
    suspend fun insertMessage(messageEntity: MessageEntity)

    @Delete
    suspend fun deleteMessage(messageEntity: MessageEntity)
}
