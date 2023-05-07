package com.space_intl.chatapp.presentation.ui.chat.model.mapper

import com.space_intl.chatapp.common.mapper.ModelMapper
import com.space_intl.chatapp.domain.model.MessageDomainModel
import com.space_intl.chatapp.presentation.ui.chat.model.MessageUIModel

class MessageDomainUIMapper: ModelMapper<MessageDomainModel, MessageUIModel>{
    override operator fun invoke(model: MessageDomainModel): MessageUIModel =
        MessageUIModel(
            id = model.id,
            message = model.message,
            dateSent = model.dateSent,
            dateSentStr = model.dateSentStr,
            userId = model.userId,
            isDelivered = model.isDelivered
        )
}