package com.space_intl.chatapp.domain.repository

import com.space_intl.chatapp.domain.model.MessageDomainModel
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for the chat.
 * @see [ChatRepositoryImpl]
 */
interface ChatRepository {

    /**
     * Retrieves all messages from the database.
     * @return A [Flow] of [List] of [MessageDomainModel].
     */
    fun retrieveMessages(): Flow<List<MessageDomainModel>>

    /**
     * Retrieves a message from the database.
     * @param id The id of the message to be retrieved.
     * @return A [Flow] of [MessageDomainModel].
     */
    fun retrieveMessageById(id: Int): Flow<MessageDomainModel>

    /**
     * Saves a message to the database.
     * @param messageModel The message to be saved.
     */
    suspend fun saveMessage(messageModel: MessageDomainModel)

    /**
     * Removes a message from the database.
     * @param messageModel The message to be removed.
     */
    suspend fun removeMessage(messageModel: MessageDomainModel)
}
