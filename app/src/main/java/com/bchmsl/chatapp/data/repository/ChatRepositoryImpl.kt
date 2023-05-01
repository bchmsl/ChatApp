package com.bchmsl.chatapp.data.repository

import com.bchmsl.chatapp.data.local.dao.MessageDao
import com.bchmsl.chatapp.data.mapper.toEntity
import com.bchmsl.chatapp.data.mapper.toMessage
import com.bchmsl.chatapp.domain.model.MessageModel
import com.bchmsl.chatapp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ChatRepositoryImpl(
    private val dao: MessageDao
) : ChatRepository {
    override fun retrieveMessages(): Flow<List<MessageModel>> =
        dao.retrieveAll().map {entities ->
            entities.map { entity -> entity.toMessage()}
        }

    override suspend fun saveMessage(messageModel: MessageModel) {
        dao.insertMessage(messageModel.toEntity())
    }
}