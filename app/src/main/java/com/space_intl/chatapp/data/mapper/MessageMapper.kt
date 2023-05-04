package com.space_intl.chatapp.data.mapper

import com.space_intl.chatapp.data.local.model.MessageEntity
import com.space_intl.chatapp.domain.model.MessageModel

fun MessageEntity.toMessage(): MessageModel =
    MessageModel(
        id = id,
        message = message,
        dateSent = dateSent,
        userId = userId
    )

fun MessageModel.toEntity(): MessageEntity =
    MessageEntity(
        message = message,
        dateSent = dateSent,
        userId = userId
    )