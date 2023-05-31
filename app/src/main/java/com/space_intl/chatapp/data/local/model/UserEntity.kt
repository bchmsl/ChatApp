package com.space_intl.chatapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class for the user.
 * @see Entity
 */
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userName: String
)
