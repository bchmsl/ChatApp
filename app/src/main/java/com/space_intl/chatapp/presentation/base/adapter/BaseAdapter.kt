package com.space_intl.chatapp.presentation.base.adapter

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<MODEL: Any, VB : ViewBinding, VH : BaseAdapter.BaseViewHolder<MODEL, VB>>(
    private val listener: () -> String,
) : ListAdapter<MODEL, VH>(CustomItemCallback<MODEL>()) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position), listener)
    }

    abstract class BaseViewHolder<MODEL : Any, VB : ViewBinding>(binding: VB) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun onBind(item: MODEL, listener: () -> String)
    }
}