package com.space_intl.chatapp.di.module

import com.space_intl.chatapp.data.mapper.MessageDomainEntityMapper
import com.space_intl.chatapp.data.mapper.MessageEntityDomainMapper
import com.space_intl.chatapp.data.repository.ChatRepositoryImpl
import com.space_intl.chatapp.domain.repository.ChatRepository
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
}
