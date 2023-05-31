package com.space_intl.chatapp.presentation.ui.chat.model.mapper

import com.space_intl.chatapp.common.mapper.ModelMapper
import com.space_intl.chatapp.domain.model.MessageDomainModel
import com.space_intl.chatapp.presentation.ui.chat.model.MessageUIModel

/**
 * Mapper class used to transform [MessageUIModel] (in the presentation layer) to
 * [MessageDomainModel] in the domain layer.
 * @see ModelMapper
 * @see MessageUIModel
 * @see MessageDomainModel
 */
class MessageUIDomainMapper : ModelMapper<MessageUIModel, MessageDomainModel> {
    override operator fun invoke(model: MessageUIModel): MessageDomainModel =
        MessageDomainModel(
            message = model.message,
            dateSent = model.dateSent,
            userName = model.userName,
            isDelivered = model.isDelivered,
            id = model.id
        )
}
