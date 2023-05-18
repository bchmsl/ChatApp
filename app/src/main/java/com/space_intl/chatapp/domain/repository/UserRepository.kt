package com.space_intl.chatapp.domain.repository

import com.space_intl.chatapp.domain.model.UserDomainModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun saveUser(userDomainModel: UserDomainModel)
    fun retrieveUsers(): Flow<List<UserDomainModel>>
}
