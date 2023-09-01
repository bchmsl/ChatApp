package com.space_intl.chatapp.common.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Extension function to execute coroutine in ViewModel.
 */

fun ViewModel.executeAsync(
    coroutineContext: CoroutineContext = Dispatchers.Main,
    block: suspend () -> Unit
) {
    this.viewModelScope.launch(coroutineContext) {
        block.invoke()
    }
}
