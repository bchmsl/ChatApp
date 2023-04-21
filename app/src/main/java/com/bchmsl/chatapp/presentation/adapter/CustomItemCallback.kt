package com.bchmsl.chatapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bchmsl.chatapp.presentation.model.ModelWithId

class CustomItemCallback<T : ModelWithId<T>> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem
}