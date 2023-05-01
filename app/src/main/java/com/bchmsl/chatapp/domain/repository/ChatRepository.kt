package com.bchmsl.chatapp.domain.repository

import com.bchmsl.chatapp.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun retrieveMessages(): Flow<List<MessageModel>>
    suspend fun saveMessage(messageModel: MessageModel)
}