package com.space_intl.chatapp.presentation.ui.chat.model

import com.space_intl.chatapp.common.extensions.toFormattedDate

/**
 * Representation for a [MessageUIModel] fetched from an external layer data source
 */
data class MessageUIModel(
    val message: String,
    val dateSent: Long,
    val userId: String,
    val dateSentStr: String = dateSent.toFormattedDate(),
    val isDelivered: Boolean = true,
    val id: Int = 0
)
