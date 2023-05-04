package com.space_intl.chatapp.data.repository

import com.space_intl.chatapp.data.local.dao.MessageDao
import com.space_intl.chatapp.data.mapper.toEntity
import com.space_intl.chatapp.data.mapper.toDomain
import com.space_intl.chatapp.domain.model.MessageDomainModel
import com.space_intl.chatapp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChatRepositoryImpl(
    private val dao: MessageDao
) : ChatRepository {
    override fun retrieveMessages(): Flow<List<MessageDomainModel>> =
        dao.retrieveAll().map {entities ->
            entities.map { entity -> entity.toDomain()}
        }

    override suspend fun saveMessage(messageModel: MessageDomainModel) {
        dao.insertMessage(messageModel.toEntity())
    }
}