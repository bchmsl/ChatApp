package com.space_intl.chatapp.presentation.base.adapter

/**
 * Interface for adapter listener.
 * @param T as a generic type.
 */
interface AdapterFlipper<T : Any> {
    fun userName(): T
}
