package com.space_intl.chatapp.di.module

import com.space_intl.chatapp.data.repository.ChatRepositoryImpl
import com.space_intl.chatapp.domain.repository.ChatRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ChatRepository> { ChatRepositoryImpl(get()) }
}
