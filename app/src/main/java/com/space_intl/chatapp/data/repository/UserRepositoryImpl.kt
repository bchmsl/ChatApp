package com.space_intl.chatapp.data.repository

import com.space_intl.chatapp.data.local.dao.UserDao
import com.space_intl.chatapp.data.mapper.user.UserDomainEntityMapper
import com.space_intl.chatapp.data.mapper.user.UserEntityDomainMapper
import com.space_intl.chatapp.domain.model.UserDomainModel
import com.space_intl.chatapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val dao: UserDao,
    private val domainEntityMapper: UserDomainEntityMapper,
    private val entityDomainMapper: UserEntityDomainMapper
) : UserRepository {
    override suspend fun saveUser(userDomainModel: UserDomainModel) {
        dao.insertUser(domainEntityMapper(userDomainModel))
    }

    override fun retrieveUsers(): Flow<List<UserDomainModel>> =
        dao.retrieveUsers().map { users ->
            users.map { user -> entityDomainMapper(user) }
        }
}
