package com.space_intl.chatapp.presentation.base.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class CustomItemCallback<MDL : Any> : DiffUtil.ItemCallback<MDL>() {
    override fun areItemsTheSame(oldItem: MDL, newItem: MDL): Boolean =
        oldItem === newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: MDL, newItem: MDL): Boolean =
        oldItem.hashCode() == newItem.hashCode()
}