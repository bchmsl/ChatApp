package com.space_intl.chatapp.common.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun Fragment.executeAsync(
    coroutineContext: CoroutineContext = Dispatchers.Main,
    lifecycleState: Lifecycle.State = Lifecycle.State.RESUMED,
    block: suspend () -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch(coroutineContext) {
        viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
            block.invoke()
        }
    }
}

fun <T> Fragment.collectAsync(flow: Flow<T>, block: suspend (T) -> Unit) {
    this.executeAsync{
        flow.collect{
            block.invoke(it)
        }
    }
}

val Fragment.fragmentContext get() = let { context } ?: requireContext()
val Fragment.fragmentActivity get() = let { activity } ?: requireActivity()
