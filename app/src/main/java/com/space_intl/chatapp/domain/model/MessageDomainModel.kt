package com.space_intl.chatapp.domain.model


data class MessageDomainModel(
    val message: String,
    val dateSent: Long,
    val userId: String,
    val isDelivered: Boolean = true,
    val id: Int = 0
)