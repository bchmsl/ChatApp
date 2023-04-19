package com.bchmsl.chatapp.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bchmsl.chatapp.presentation.model.MessageUiModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _messagesHistoryState = MutableSharedFlow<List<MessageUiModel>>()
    val messagesHistoryState get() = _messagesHistoryState.asSharedFlow()

    fun retrieveMessages() {
        viewModelScope.launch {
            // Test to get messages
            val messages = MessageUiModel.messagesTestList

            _messagesHistoryState.emit(messages)
        }
    }

    fun sendMessage(message: MessageUiModel) {
        viewModelScope.launch {
            // Test to get messages
            MessageUiModel.messagesTestList.add(message)
        }
    }
}