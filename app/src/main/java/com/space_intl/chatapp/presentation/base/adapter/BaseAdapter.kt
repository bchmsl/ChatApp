package com.space_intl.chatapp.presentation.base.adapter

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T: Any, VB : ViewBinding, VH : BaseAdapter.BaseViewHolder<T, VB>>(
    private val listener: () -> String,
) : ListAdapter<T, VH>(CustomItemCallback<T>()) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position), listener)
    }

    abstract class BaseViewHolder<T : Any, VB : ViewBinding>(binding: VB) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun onBind(item: T, listener: () -> String)
    }
}