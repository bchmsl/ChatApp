package com.space_intl.chatapp.presentation.ui.chat.viewmodel

import androidx.lifecycle.ViewModel
import com.space_intl.chatapp.common.extensions.executeAsync
import com.space_intl.chatapp.domain.repository.ChatRepository
import com.space_intl.chatapp.presentation.ui.chat.model.MessageUiModel
import com.space_intl.chatapp.presentation.ui.chat.model.mapper.MessageDomainUiMapper
import com.space_intl.chatapp.presentation.ui.chat.model.mapper.MessageUiDomainMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*

class ChatViewModel(
    private val chatRepository: ChatRepository,
    private val uiDomainMapper: MessageUiDomainMapper,
    private val domainUiMapper: MessageDomainUiMapper
) : ViewModel() {

    private val _messagesHistoryState = MutableStateFlow<List<MessageUiModel>>(emptyList())
    val messagesHistoryState get() = _messagesHistoryState.asStateFlow()

    private val _messageSentState = MutableStateFlow(false)
    val messageSentState get() = _messageSentState.asStateFlow()

    private val _messageState = MutableStateFlow("")
    val messageState get() = _messageState.asStateFlow()

    fun retrieveMessages() {
        executeAsync(Dispatchers.IO) {
            chatRepository.retrieveMessages().collect { messages ->
                _messagesHistoryState.emit(messages.map { model -> domainUiMapper.mapModel(model) })
            }
        }
    }

    fun sendMessage(etMessage: String, user: String, isOnline: Boolean) {
        executeAsync(Dispatchers.IO) {
            if (etMessage.isNotBlank()) {
                val message = MessageUiModel(
                    etMessage,
                    Calendar.getInstance().timeInMillis,
                    user,
                    isDelivered = isOnline
                )
                chatRepository.saveMessage(uiDomainMapper.mapModel(message))
                _messageSentState.emit(true)
            }
        }
    }

    fun saveMessageState(message: String) {
        _messageState.tryEmit(message)
    }
}