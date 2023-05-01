package com.space_intl.chatapp.domain.repository

import com.space_intl.chatapp.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun retrieveMessages(): Flow<List<MessageModel>>
    suspend fun saveMessage(messageModel: MessageModel)
}