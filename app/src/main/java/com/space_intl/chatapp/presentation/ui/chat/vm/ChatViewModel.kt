package com.space_intl.chatapp.presentation.ui.chat.vm

import androidx.lifecycle.ViewModel
import com.space_intl.chatapp.common.extensions.executeAsync
import com.space_intl.chatapp.domain.model.MessageModel
import com.space_intl.chatapp.domain.repository.ChatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*

class ChatViewModel(
    private val chatRepository: ChatRepository
) : ViewModel() {

    private val _messagesHistoryState = MutableStateFlow<List<MessageModel>>(emptyList())
    val messagesHistoryState get() = _messagesHistoryState.asStateFlow()

    private val _messageSentState = MutableStateFlow(false)
    val messageSentState get() = _messageSentState

    fun retrieveMessages() {
        executeAsync(Dispatchers.IO) {
            chatRepository.retrieveMessages().collect {messages ->
                _messagesHistoryState.emit(messages)
            }
        }
    }

    fun sendMessage(etMessage: String, user: String) {
        executeAsync(Dispatchers.IO) {
            if (etMessage.isNotBlank()) {
                val message = MessageModel(
                    etMessage,
                    Calendar.getInstance().timeInMillis,
                    user
                )
                chatRepository.saveMessage(message)
                _messageSentState.emit(true)
            }
        }
    }
}