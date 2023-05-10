package com.space_intl.chatapp.presentation.base.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

/**
 * Custom item callback for the recycler view.
 * @param MODEL the model type of the recycler view item.
 * @see DiffUtil.ItemCallback
 */
class CustomItemCallback<MODEL : Any> : DiffUtil.ItemCallback<MODEL>() {
    override fun areItemsTheSame(oldItem: MODEL, newItem: MODEL): Boolean =
        oldItem === newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: MODEL, newItem: MODEL): Boolean =
        oldItem == newItem
}
