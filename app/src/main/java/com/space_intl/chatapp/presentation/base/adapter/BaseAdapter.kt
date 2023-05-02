package com.space_intl.chatapp.presentation.base.adapter

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.space_intl.chatapp.presentation.model.ModelWithId

abstract class BaseAdapter<T : ModelWithId<T>, VB : ViewBinding, VH : BaseAdapter.BaseViewHolder<T, VB>>(
    private val listener: AdapterListener,
) : ListAdapter<T, VH>(CustomItemCallback<T>()) {

    abstract class BaseViewHolder<T : ModelWithId<T>, VB : ViewBinding>(binding: VB) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun onBind(item: T, listener: AdapterListener)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position), listener)
    }
}