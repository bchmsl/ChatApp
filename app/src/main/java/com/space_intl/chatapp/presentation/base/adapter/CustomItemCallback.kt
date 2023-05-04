package com.space_intl.chatapp.presentation.base.adapter

import androidx.recyclerview.widget.DiffUtil
import com.space_intl.chatapp.presentation.model.ModelWithId

class CustomItemCallback<T : ModelWithId<T>> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.hashCode() == newItem.hashCode()
}