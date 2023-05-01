package com.bchmsl.chatapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat")
data class MessageEntity(
    val message: String,
    val dateSent: Long,
    val sentBy: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
    )