package com.space_intl.chatapp.domain.model

/**
 * Domain model class for the message.
 */
data class MessageDomainModel(
    val message: String,
    val dateSent: Long,
    val userName: String,
    val isDelivered: Boolean = true,
    val id: Int = 0
)
