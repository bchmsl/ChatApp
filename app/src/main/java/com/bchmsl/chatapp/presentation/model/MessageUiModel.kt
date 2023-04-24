package com.bchmsl.chatapp.presentation.model

data class MessageUiModel(
    override val id: Int,
    val message: String,
    val dateSent: Long,
    val isSentByFirstUser: Boolean
): ModelWithId<MessageUiModel>() {



    // This comp is temporary to test messages functionality
    companion object {
        var id = 0
        val messagesTestList = mutableListOf<MessageUiModel>()

    }
}