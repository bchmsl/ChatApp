package com.space_intl.chatapp.di.module

import com.space_intl.chatapp.presentation.ui.chat.model.mapper.MessageDomainUiMapper
import com.space_intl.chatapp.presentation.ui.chat.model.mapper.MessageUiDomainMapper
import com.space_intl.chatapp.presentation.ui.chat.viewmodel.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ChatViewModel(get(), MessageUiDomainMapper(), MessageDomainUiMapper()) }
}

