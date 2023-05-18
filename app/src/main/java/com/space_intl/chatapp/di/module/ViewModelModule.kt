package com.space_intl.chatapp.di.module

import com.space_intl.chatapp.presentation.ui.chat.model.mapper.MessageDomainUIMapper
import com.space_intl.chatapp.presentation.ui.chat.model.mapper.MessageUIDomainMapper
import com.space_intl.chatapp.presentation.ui.chat.viewmodel.ChatViewModel
import com.space_intl.chatapp.presentation.ui.users.model.mapper.UserDomainUIMapper
import com.space_intl.chatapp.presentation.ui.users.model.mapper.UserUIDomainMapper
import com.space_intl.chatapp.presentation.ui.users.viewmodel.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Module for the view model.
 * @see module
 */
val viewModelModule = module {
    viewModel { ChatViewModel(get(), MessageUIDomainMapper(), MessageDomainUIMapper()) }
    viewModel { UsersViewModel(get(), UserDomainUIMapper(), UserUIDomainMapper()) }
}

