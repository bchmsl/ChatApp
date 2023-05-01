package com.space_intl.chatapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat")
data class MessageEntity(
    val message: String,
    val dateSent: Long,
    val user: User,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
    )