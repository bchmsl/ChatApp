package com.bchmsl.chatapp.data.repository

import com.bchmsl.chatapp.data.local.dao.MessageDao
import com.bchmsl.chatapp.data.mapper.toEntity
import com.bchmsl.chatapp.data.mapper.toMessage
import com.bchmsl.chatapp.domain.model.MessageModel
import com.bchmsl.chatapp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChatRepositoryImpl(
    private val dao: MessageDao
) : ChatRepository {
    override suspend fun retrieveMessages(): Flow<List<MessageModel>> = flow {
        emit(dao.retrieveAll().map { it.toMessage() })
    }

    override suspend fun saveMessage(messageModel: MessageModel) {
        dao.insertMessage(messageModel.toEntity())
    }
}