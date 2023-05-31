package com.space_intl.chatapp.data.repository

import com.space_intl.chatapp.data.local.dao.MessageDao
import com.space_intl.chatapp.data.mapper.MessageDomainEntityMapper
import com.space_intl.chatapp.data.mapper.MessageEntityDomainMapper
import com.space_intl.chatapp.domain.model.MessageDomainModel
import com.space_intl.chatapp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of [ChatRepository].
 * @see ChatRepository
 */
class ChatRepositoryImpl(
    private val dao: MessageDao,
    private val domainEntityMapper: MessageDomainEntityMapper,
    private val entityDomainMapper: MessageEntityDomainMapper
) : ChatRepository {

    /**
     * Retrieves all messages from the database.
     * @return A [Flow] of [List] of [MessageDomainModel].
     * @see ChatRepository.retrieveMessages
     */
    override fun retrieveMessages(): Flow<List<MessageDomainModel>> =
        dao.retrieveAll().map { entities ->
            entities.map { entity ->
                entityDomainMapper(entity)
            }
        }

    /**
     * Retrieves a message from the database.
     * @param id The id of the message to be retrieved.
     * @return A [Flow] of [MessageDomainModel].
     * @see ChatRepository.retrieveMessageById
     */
    override fun retrieveMessageById(id: Int): Flow<MessageDomainModel> =
        dao.retrieveMessageById(id).map { entity ->
            entityDomainMapper(entity)
        }

    /**
     * Saves a message to the database.
     * @param messageModel The message to be saved.
     * @see ChatRepository.saveMessage
     */
    override suspend fun saveMessage(messageModel: MessageDomainModel) {
        dao.insertMessage(domainEntityMapper(messageModel))
    }

    /**
     * Removes a message from the database.
     * @param messageModel The message to be removed.
     * @see ChatRepository.removeMessage
     */
    override suspend fun removeMessage(messageModel: MessageDomainModel) {
        dao.deleteMessage(domainEntityMapper(messageModel))
    }
}
