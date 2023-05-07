package com.space_intl.chatapp.data.mapper

import com.space_intl.chatapp.common.mapper.ModelMapper
import com.space_intl.chatapp.data.local.model.MessageEntity
import com.space_intl.chatapp.domain.model.MessageDomainModel

class MessageDomainEntityMapper: ModelMapper<MessageDomainModel, MessageEntity> {
    override fun mapModel(model: MessageDomainModel): MessageEntity =
        MessageEntity(
            message = model.message,
            dateSent = model.dateSent,
            userId = model.userId,
            isDelivered = model.isDelivered
        )
}
