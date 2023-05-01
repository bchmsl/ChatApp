package com.space_intl.chatapp.data.mapper

import com.space_intl.chatapp.data.local.model.MessageEntity
import com.space_intl.chatapp.data.local.model.User
import com.space_intl.chatapp.domain.model.MessageModel
import com.space_intl.chatapp.presentation.model.UserTags

fun MessageEntity.toMessage(): MessageModel =
    MessageModel(
        id = id,
        message = message,
        dateSent = dateSent,
        sentBy = UserTags.valueOf(user.name)
    )

fun MessageModel.toEntity(): MessageEntity =
    MessageEntity(
        message = message,
        dateSent = dateSent,
        user = User.valueOf(sentBy.name)
    )