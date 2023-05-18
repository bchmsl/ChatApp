package com.space_intl.chatapp.di.module

import com.space_intl.chatapp.data.mapper.message.MessageDomainEntityMapper
import com.space_intl.chatapp.data.mapper.message.MessageEntityDomainMapper
import com.space_intl.chatapp.data.mapper.user.UserDomainEntityMapper
import com.space_intl.chatapp.data.mapper.user.UserEntityDomainMapper
import com.space_intl.chatapp.data.repository.ChatRepositoryImpl
import com.space_intl.chatapp.data.repository.UserRepositoryImpl
import com.space_intl.chatapp.domain.repository.ChatRepository
import com.space_intl.chatapp.domain.repository.UserRepository
import org.koin.dsl.module

/**
 * Module for the repository.
 * @see module
 */
val repositoryModule = module {
    single<ChatRepository> {
        ChatRepositoryImpl(
            get(),
            MessageDomainEntityMapper(),
            MessageEntityDomainMapper()
        )
    }
    single<UserRepository> {
        UserRepositoryImpl(
            get(),
            UserDomainEntityMapper(),
            UserEntityDomainMapper()
        )
    }
}
