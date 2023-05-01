package com.bchmsl.chatapp.domain.model

import com.bchmsl.chatapp.presentation.model.ModelWithId
import com.bchmsl.chatapp.presentation.model.UserTags

data class MessageModel(
    val message: String,
    val dateSent: Long,
    val sentBy: UserTags,
    override val id: Int = 0
): ModelWithId<MessageModel>()