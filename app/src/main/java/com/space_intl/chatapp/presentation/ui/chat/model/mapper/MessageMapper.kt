package com.space_intl.chatapp.presentation.ui.chat.model.mapper

import com.space_intl.chatapp.domain.model.MessageDomainModel
import com.space_intl.chatapp.presentation.ui.chat.model.MessageUiModel

fun MessageDomainModel.toUi(): MessageUiModel =
    MessageUiModel(
        id = id,
        message = message,
        dateSent = dateSent,
        dateSentStr = dateSentStr,
        userId = userId,
        isDelivered = isDelivered
    )

fun MessageUiModel.toDomain(): MessageDomainModel =
    MessageDomainModel(
        message = message,
        dateSent = dateSent,
        dateSentStr = dateSentStr,
        userId = userId,
        isDelivered = isDelivered,
    )