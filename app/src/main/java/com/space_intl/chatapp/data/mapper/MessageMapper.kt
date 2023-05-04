package com.space_intl.chatapp.data.mapper

import com.space_intl.chatapp.data.local.model.MessageEntity
import com.space_intl.chatapp.domain.model.MessageDomainModel

fun MessageEntity.toDomain(): MessageDomainModel =
    MessageDomainModel(
        id = id,
        message = message,
        dateSent = dateSent,
        userId = userId,
        isDelivered = isDelivered
    )

fun MessageDomainModel.toEntity(): MessageEntity =
    MessageEntity(
        message = message,
        dateSent = dateSent,
        userId = userId,
        isDelivered = isDelivered
    )