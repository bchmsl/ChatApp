package com.bchmsl.chatapp.data.mapper

import com.bchmsl.chatapp.data.local.model.MessageEntity
import com.bchmsl.chatapp.domain.model.MessageModel
import com.bchmsl.chatapp.presentation.model.UserTags

fun MessageEntity.toMessage(): MessageModel =
    MessageModel(
        id = id,
        message = message,
        dateSent = dateSent,
        sentBy = UserTags.valueOf(sentBy)
    )

fun MessageModel.toEntity(): MessageEntity =
    MessageEntity(
        message = message,
        dateSent = dateSent,
        sentBy = sentBy.name
    )