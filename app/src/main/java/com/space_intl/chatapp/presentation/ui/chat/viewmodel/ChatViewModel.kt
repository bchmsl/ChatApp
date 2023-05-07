package com.space_intl.chatapp.presentation.ui.chat.viewmodel

import androidx.lifecycle.ViewModel
import com.space_intl.chatapp.common.extensions.executeAsync
import com.space_intl.chatapp.domain.repository.ChatRepository
import com.space_intl.chatapp.presentation.ui.chat.model.MessageUIModel
import com.space_intl.chatapp.presentation.ui.chat.model.mapper.MessageDomainUIMapper
import com.space_intl.chatapp.presentation.ui.chat.model.mapper.MessageUIDomainMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*

class ChatViewModel(
    private val chatRepository: ChatRepository,
    private val uiDomainMapper: MessageUIDomainMapper,
    private val domainUiMapper: MessageDomainUIMapper
) : ViewModel() {

    private val _messagesHistoryState = MutableStateFlow<List<MessageUIModel>>(emptyList())
    val messagesHistoryState get() = _messagesHistoryState.asStateFlow()

    private val _messageSentState = MutableStateFlow(false)
    val messageSentState get() = _messageSentState.asStateFlow()

    fun retrieveMessages(userId: String) {
        executeAsync(Dispatchers.IO) {
            chatRepository.retrieveMessages().collect { messages ->
                _messagesHistoryState.emit(
                    filterMessages(
                        messages.map { model -> domainUiMapper(model) },
                        userId
                    )
                )
            }
        }
    }

    fun sendMessage(etMessage: String, user: String, isOnline: Boolean) {
        executeAsync(Dispatchers.IO) {
            if (etMessage.isNotBlank()) {
                val message = MessageUIModel(
                    etMessage,
                    Calendar.getInstance().timeInMillis,
                    user,
                    isDelivered = isOnline
                )
                chatRepository.saveMessage(uiDomainMapper(message))
                _messageSentState.emit(true)
            }
        }
    }

    private fun filterMessages(
        messages: List<MessageUIModel>,
        userId: String
    ): List<MessageUIModel> {
        return messages.filter {
            val isMessageSent = it.userId == userId
            val isMessageShown = (!it.isDelivered && isMessageSent) || it.isDelivered
            isMessageShown
        }
    }

}