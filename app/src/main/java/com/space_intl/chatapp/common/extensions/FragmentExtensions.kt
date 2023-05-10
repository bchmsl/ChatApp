package com.space_intl.chatapp.common.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Extension function to execute coroutine in fragment.
 * @param coroutineContext Coroutine context to execute coroutine.
 * @param lifecycleState Lifecycle state to repeat coroutine.
 * @param block Coroutine block to execute.
 *
 * [Dispatchers.Main] is used as default coroutine context.
 * [Lifecycle.State.RESUMED] is used as default lifecycle state.
 * @see <a href="https://developer.android.com/kotlin/flow#lifecycle-aware-flows">Lifecycle-aware flows</a>
 */

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

/**
 * Extension function to collect flow asynchronously in fragment.
 * @param flow Flow to collect.
 * @param block Coroutine block to execute.
 */

fun <T> Fragment.collectAsync(flow: Flow<T>, block: suspend (T) -> Unit) {
    this.executeAsync {
        flow.collect {
            block.invoke(it)
        }
    }
}

/**
 * Extension properties to get fragment context and activity.
 */

val Fragment.fragmentContext get() = let { context } ?: requireContext()
val Fragment.fragmentActivity get() = let { activity } ?: requireActivity()
