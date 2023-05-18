package com.space_intl.chatapp.presentation.ui.chat.viewmodel

import androidx.lifecycle.ViewModel
import com.space_intl.chatapp.common.extensions.executeAsync
import com.space_intl.chatapp.domain.model.MessageDomainModel
import com.space_intl.chatapp.domain.repository.ChatRepository
import com.space_intl.chatapp.presentation.ui.chat.adapter.ChatAdapter
import com.space_intl.chatapp.presentation.ui.chat.fragment.ChatFragment
import com.space_intl.chatapp.presentation.ui.chat.model.MessageUIModel
import com.space_intl.chatapp.presentation.ui.chat.model.mapper.MessageDomainUIMapper
import com.space_intl.chatapp.presentation.ui.chat.model.mapper.MessageUIDomainMapper
import com.space_intl.chatapp.service.MessageReceiver
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*

/**
 * ViewModel for [ChatFragment]
 * @param chatRepository [ChatRepository] to retrieve, send and remove messages
 * @param uiDomainMapper [MessageUIDomainMapper] to map [MessageUIModel] to [MessageDomainModel]
 * @param domainUiMapper [MessageDomainUIMapper] to map [MessageDomainModel] to [MessageUIModel]
 * @see ViewModel
 * @see ChatRepository
 * @see MessageUIDomainMapper
 * @see MessageDomainUIMapper
 */
class ChatViewModel(
    private val chatRepository: ChatRepository,
    private val uiDomainMapper: MessageUIDomainMapper,
    private val domainUiMapper: MessageDomainUIMapper
) : ViewModel() {

    /**
     * Messages History State for [ChatAdapter]
     */
    private val _messagesHistoryState = MutableStateFlow<List<MessageUIModel>>(emptyList())
    val messagesHistoryState get() = _messagesHistoryState.asStateFlow()

    /**
     * Message Sent State for [MessageReceiver]
     * Used to notify [ChatFragment] that message was sent
     *
     */
    private val _messageSentState = MutableStateFlow(false)
    val messageSentState get() = _messageSentState.asStateFlow()

    /**
     * Message Sent State for [MessageReceiver]
     * Used to notify [ChatFragment] that message was sent
     *
     */
    private val _sentMessageState = MutableStateFlow<MessageUIModel?>(null)
    val sentMessageState get() = _sentMessageState.asStateFlow()

    /**
     * Function to retrieve messages from [ChatRepository]
     * @param userId to filter messages by user
     * @see ChatRepository
     */
    fun retrieveMessages(userId: String) {
        executeAsync(IO) {
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

    /**
     * Function to retrieve message by id from [ChatRepository]
     * @param id to filter messages by id
     * @see ChatRepository
     */
    fun retrieveMessageById(id: Int) {
        executeAsync(IO) {
            chatRepository.retrieveMessageById(id).collect { message ->
                _sentMessageState.emit(domainUiMapper(message))
            }
        }
    }

    /**
     * Function to send message to [ChatRepository]
     * @param messageBody to send
     * @param user to send message from
     * @param isOnline to check if user is online
     * @see ChatRepository
     */
    fun sendMessage(messageBody: String, user: String, isOnline: Boolean) {
        executeAsync(IO) {
            if (messageBody.isNotBlank()) {
                val message = MessageUIModel(
                    messageBody,
                    Calendar.getInstance().timeInMillis,
                    user,
                    isDelivered = isOnline
                )
                chatRepository.saveMessage(uiDomainMapper(message))
                _messageSentState.emit(true)
            }
        }
    }

    /**
     * Function to remove message from [ChatRepository]
     * @param messageUIModel to remove
     * @see ChatRepository
     */
    fun removeMessage(messageUIModel: MessageUIModel) {
        executeAsync(IO) {
            chatRepository.removeMessage(uiDomainMapper(messageUIModel))
        }
    }

    /**
     * Function to filter which messages to be shown by [ChatAdapter]
     * Filtered if message is not delivered and is to be shown
     * @param messages to filter
     * @param userId to filter by
     * @return filtered messages
     */
    private fun filterMessages(
        messages: List<MessageUIModel>,
        userId: String
    ): List<MessageUIModel> {
        return messages.filter {
            val isMessageSent = it.userName == userId
            val isMessageShown = (!it.isDelivered && isMessageSent) || it.isDelivered
            isMessageShown
        }
    }


}
