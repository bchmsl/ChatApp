package com.space_intl.chatapp.presentation.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space_intl.chatapp.databinding.ItemUserBinding
import com.space_intl.chatapp.presentation.base.adapter.CustomItemCallback
import com.space_intl.chatapp.presentation.base.adapter.OnClickListener
import com.space_intl.chatapp.presentation.ui.users.model.UserUIModel

class UsersAdapter :
    ListAdapter<UserUIModel, UsersAdapter.UsersViewHolder>(CustomItemCallback<UserUIModel>()) {

    private var listener: OnClickListener<UserUIModel>? = null

    fun setListener(listener: OnClickListener<UserUIModel>) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersViewHolder =
        UsersViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.onBind(getItem(position), listener)
    }

    class UsersViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: UserUIModel, listener: OnClickListener<UserUIModel>?) {
            with(binding) {
                userNameTextView.text = item.userName
                root.setOnClickListener { listener?.onClick(item, adapterPosition) }
            }
        }
    }
}
