package com.space_intl.chatapp.presentation.ui.chat.model

import com.space_intl.chatapp.common.extensions.toFormattedDate

data class MessageUiModel(
    val message: String,
    val dateSent: Long,
    val userId: String,
    val dateSentStr: String = dateSent.toFormattedDate(),
    val isDelivered: Boolean = true,
    val id: Int = 0
)