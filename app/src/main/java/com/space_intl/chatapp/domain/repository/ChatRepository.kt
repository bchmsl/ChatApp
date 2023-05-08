package com.space_intl.chatapp.domain.repository

import com.space_intl.chatapp.domain.model.MessageDomainModel
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun retrieveMessages(): Flow<List<MessageDomainModel>>
    suspend fun saveMessage(messageModel: MessageDomainModel)
    suspend fun removeMessage(messageModel: MessageDomainModel)
}