package com.space_intl.chatapp.presentation.ui.chat.model.mapper

import com.space_intl.chatapp.common.mapper.ModelMapper
import com.space_intl.chatapp.domain.model.MessageDomainModel
import com.space_intl.chatapp.presentation.ui.chat.model.MessageUIModel

class MessageUIDomainMapper: ModelMapper<MessageUIModel, MessageDomainModel> {
    override operator fun invoke(model: MessageUIModel): MessageDomainModel =
        MessageDomainModel(
            message = model.message,
            dateSent = model.dateSent,
            userId = model.userId,
            isDelivered = model.isDelivered,
            id = model.id
        )
}