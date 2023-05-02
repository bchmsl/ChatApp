package com.space_intl.chatapp.domain.model

import com.space_intl.chatapp.presentation.model.ModelWithId

data class MessageModel(
    val message: String,
    val dateSent: Long,
    val userId: String,
    override val id: Int = 0
): ModelWithId<MessageModel>()