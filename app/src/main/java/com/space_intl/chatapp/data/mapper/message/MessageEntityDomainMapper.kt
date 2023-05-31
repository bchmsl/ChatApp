package com.space_intl.chatapp.data.mapper.message

import com.space_intl.chatapp.common.mapper.ModelMapper
import com.space_intl.chatapp.data.local.model.MessageEntity
import com.space_intl.chatapp.domain.model.MessageDomainModel


/**
 * Mapper class used to transform [MessageEntity] (in the data layer) to [MessageDomainModel] in the
 * domain layer.
 * @see ModelMapper
 * @see MessageEntity
 * @see MessageDomainModel
 */
class MessageEntityDomainMapper : ModelMapper<MessageEntity, MessageDomainModel> {
    override operator fun invoke(model: MessageEntity): MessageDomainModel =
        MessageDomainModel(
            id = model.id,
            message = model.message,
            dateSent = model.dateSent,
            userName = model.userName,
            isDelivered = model.isDelivered
        )
}
