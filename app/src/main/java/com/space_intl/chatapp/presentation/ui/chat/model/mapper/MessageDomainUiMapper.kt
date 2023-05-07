package com.space_intl.chatapp.presentation.ui.chat.model.mapper

import com.space_intl.chatapp.common.mapper.ModelMapper
import com.space_intl.chatapp.domain.model.MessageDomainModel
import com.space_intl.chatapp.presentation.ui.chat.model.MessageUiModel

class MessageDomainUiMapper: ModelMapper<MessageDomainModel, MessageUiModel>{
    override fun mapModel(model: MessageDomainModel): MessageUiModel =
        MessageUiModel(
            id = model.id,
            message = model.message,
            dateSent = model.dateSent,
            dateSentStr = model.dateSentStr,
            userId = model.userId,
            isDelivered = model.isDelivered
        )
}