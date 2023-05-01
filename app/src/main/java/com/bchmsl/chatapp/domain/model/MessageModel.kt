package com.bchmsl.chatapp.domain.model

import com.bchmsl.chatapp.presentation.model.ModelWithId
import com.bchmsl.chatapp.presentation.model.UserTags

data class MessageModel(
    override val id: Int,
    val message: String,
    val dateSent: Long,
    val sentBy: UserTags
): ModelWithId<MessageModel>() {



    // This comp is temporary to test messages functionality
    companion object {
        var id = 0
        val messagesTestList = mutableListOf<MessageModel>()

    }
}