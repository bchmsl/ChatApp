package com.space_intl.chatapp.domain.repository

import com.space_intl.chatapp.data.repository.UserRepositoryImpl
import com.space_intl.chatapp.domain.model.UserDomainModel
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for the user.
 * @see [UserRepositoryImpl]
 */
interface UserRepository {

    /**
     * Saves a user to the database.
     * @param userDomainModel The user to be saved.
     */
    suspend fun saveUser(userDomainModel: UserDomainModel)

    /**
     * Retrieves all users from the database.
     * @return A [Flow] of [List] of [UserDomainModel].
     */
    fun retrieveUsers(): Flow<List<UserDomainModel>>
}
