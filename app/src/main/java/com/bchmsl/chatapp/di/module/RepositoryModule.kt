package com.bchmsl.chatapp.di.module

import com.bchmsl.chatapp.data.repository.ChatRepositoryImpl
import com.bchmsl.chatapp.domain.repository.ChatRepository
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        single<ChatRepository> { ChatRepositoryImpl(get()) }
    }
}
