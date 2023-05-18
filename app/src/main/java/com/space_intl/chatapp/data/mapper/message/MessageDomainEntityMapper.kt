package com.space_intl.chatapp.data.mapper.message

import com.space_intl.chatapp.common.mapper.ModelMapper
import com.space_intl.chatapp.data.local.model.MessageEntity
import com.space_intl.chatapp.domain.model.MessageDomainModel


/**
 * Mapper class used to transform [MessageDomainModel] (in the domain layer) to [MessageEntity] in the
 * data layer.
 * @see ModelMapper
 * @see MessageDomainModel
 * @see MessageEntity
 */
class MessageDomainEntityMapper : ModelMapper<MessageDomainModel, MessageEntity> {
    override operator fun invoke(model: MessageDomainModel): MessageEntity =
        MessageEntity(
            message = model.message,
            dateSent = model.dateSent,
            userName = model.userName,
            isDelivered = model.isDelivered,
            id = model.id
        )
}
