package com.space_intl.chatapp.data.repository

import com.space_intl.chatapp.data.local.dao.MessageDao
import com.space_intl.chatapp.data.mapper.MessageDomainEntityMapper
import com.space_intl.chatapp.data.mapper.MessageEntityDomainMapper
import com.space_intl.chatapp.domain.model.MessageDomainModel
import com.space_intl.chatapp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChatRepositoryImpl(
    private val dao: MessageDao,
    private val domainEntityMapper: MessageDomainEntityMapper,
    private val entityDomainMapper: MessageEntityDomainMapper
) : ChatRepository {
    override fun retrieveMessages(): Flow<List<MessageDomainModel>> =
        dao.retrieveAll().map { entities ->
            entities.map { entity ->
                entityDomainMapper(entity)
            }
        }

    override suspend fun saveMessage(messageModel: MessageDomainModel) {
        dao.insertMessage(domainEntityMapper(messageModel))
    }

    override suspend fun removeMessage(messageModel: MessageDomainModel) {
        dao.deleteMessage(domainEntityMapper(messageModel))
    }
}