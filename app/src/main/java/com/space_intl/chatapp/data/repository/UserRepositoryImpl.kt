package com.space_intl.chatapp.data.repository

import com.space_intl.chatapp.data.local.dao.UserDao
import com.space_intl.chatapp.data.mapper.user.UserDomainEntityMapper
import com.space_intl.chatapp.data.mapper.user.UserEntityDomainMapper
import com.space_intl.chatapp.domain.model.UserDomainModel
import com.space_intl.chatapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of [UserRepository].
 * @see UserRepository
 */
class UserRepositoryImpl(
    private val dao: UserDao,
    private val domainEntityMapper: UserDomainEntityMapper,
    private val entityDomainMapper: UserEntityDomainMapper
) : UserRepository {

    /**
     * Saves a user to the database.
     * @param userDomainModel The user to be saved.
     * @see UserRepository.saveUser
     */
    override suspend fun saveUser(userDomainModel: UserDomainModel) {
        dao.insertUser(domainEntityMapper(userDomainModel))
    }

    /**
     * Retrieves all users from the database.
     * @return A [Flow] of [List] of [UserDomainModel].
     * @see UserRepository.retrieveUsers
     */
    override fun retrieveUsers(): Flow<List<UserDomainModel>> =
        dao.retrieveUsers().map { users ->
            users.map { user -> entityDomainMapper(user) }
        }
}
