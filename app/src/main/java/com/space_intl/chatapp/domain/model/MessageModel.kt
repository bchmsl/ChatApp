package com.space_intl.chatapp.domain.model

import com.space_intl.chatapp.presentation.model.ModelWithId
import com.space_intl.chatapp.presentation.model.UserTags

data class MessageModel(
    val message: String,
    val dateSent: Long,
    val sentBy: UserTags,
    override val id: Int = 0
): ModelWithId<MessageModel>()