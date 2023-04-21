package com.bchmsl.chatapp.presentation.ui.chat

import androidx.lifecycle.ViewModel
import com.bchmsl.chatapp.common.extensions.async
import com.bchmsl.chatapp.presentation.model.MessageUiModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class UserViewModel : ViewModel() {

    private val _messagesHistoryState = MutableSharedFlow<List<MessageUiModel>>()
    val messagesHistoryState get() = _messagesHistoryState.asSharedFlow()

    fun retrieveMessages() {
        async {
            // Test to get messages
            val messages = MessageUiModel.messagesTestList

            _messagesHistoryState.emit(messages)
        }
    }

    fun sendMessage(messageBody: String, date: String, isSentByFirstUser: Boolean) {
        // Test to get messages
        MessageUiModel.id ++
        val message = MessageUiModel(MessageUiModel.id, messageBody, date, isSentByFirstUser)
        MessageUiModel.messagesTestList.add(message)
    }
}