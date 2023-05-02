package com.space_intl.chatapp.di.module

import com.space_intl.chatapp.presentation.ui.chat.vm.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ChatViewModel(get()) }
}

