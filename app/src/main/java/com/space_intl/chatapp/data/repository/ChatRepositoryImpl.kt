package com.space_intl.chatapp.data.repository

import com.space_intl.chatapp.data.local.dao.MessageDao
import com.space_intl.chatapp.data.mapper.toEntity
import com.space_intl.chatapp.data.mapper.toMessage
import com.space_intl.chatapp.domain.model.MessageModel
import com.space_intl.chatapp.domain.repository.ChatRepository
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