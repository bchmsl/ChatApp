package com.space_intl.chatapp.presentation.base.adapter

import android.view.View

/**
 * Interface for adapter listener.
 * @param T as a generic type.
 * @see [View.OnClickListener]
 */
interface OnClickListener<T : Any> {
    fun onClick(item: T, position: Int)
}
