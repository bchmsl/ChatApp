package com.space_intl.chatapp.presentation.ui.chat.model.mapper

import com.space_intl.chatapp.common.mapper.ModelMapper
import com.space_intl.chatapp.domain.model.MessageDomainModel
import com.space_intl.chatapp.presentation.ui.chat.model.MessageUiModel

class MessageUiDomainMapper: ModelMapper<MessageUiModel, MessageDomainModel> {
    override fun mapModel(model: MessageUiModel): MessageDomainModel =
        MessageDomainModel(
            message = model.message,
            dateSent = model.dateSent,
            dateSentStr = model.dateSentStr,
            userId = model.userId,
            isDelivered = model.isDelivered
        )
}