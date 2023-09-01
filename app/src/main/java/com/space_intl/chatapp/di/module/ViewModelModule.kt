package com.space_intl.chatapp.di.module

import com.space_intl.chatapp.presentation.ui.chat.model.mapper.MessageDomainUIMapper
import com.space_intl.chatapp.presentation.ui.chat.model.mapper.MessageUIDomainMapper
import com.space_intl.chatapp.presentation.ui.chat.viewmodel.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Module for the view model.
 * @see module
 */
val viewModelModule = module {
    viewModel { ChatViewModel(get(), MessageUIDomainMapper(), MessageDomainUIMapper()) }
}

