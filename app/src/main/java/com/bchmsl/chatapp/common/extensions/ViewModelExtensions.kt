package com.bchmsl.chatapp.common.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun ViewModel.async(
    coroutineContext: CoroutineContext = Dispatchers.Main,
    block: suspend () -> Unit
) {
    this.viewModelScope.launch(coroutineContext) {
        block.invoke()
    }
}