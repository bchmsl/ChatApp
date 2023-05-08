package com.space_intl.chatapp.data.mapper

import com.space_intl.chatapp.common.mapper.ModelMapper
import com.space_intl.chatapp.data.local.model.MessageEntity
import com.space_intl.chatapp.domain.model.MessageDomainModel

class MessageEntityDomainMapper: ModelMapper<MessageEntity, MessageDomainModel>{
    override operator fun invoke(model: MessageEntity): MessageDomainModel =
        MessageDomainModel(
            id = model.id,
            message = model.message,
            dateSent = model.dateSent,
            userId = model.userId,
            isDelivered = model.isDelivered
        )
}