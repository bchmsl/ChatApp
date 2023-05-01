package com.bchmsl.chatapp.di.module

import com.bchmsl.chatapp.presentation.ui.chat.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ChatViewModel(get()) }
}

