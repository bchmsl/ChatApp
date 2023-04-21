package com.bchmsl.chatapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bchmsl.chatapp.databinding.LayoutMessageItemBinding
import com.bchmsl.chatapp.presentation.model.MessageUiModel
import com.bchmsl.chatapp.presentation.model.UserTags

class ChatAdapter(private val user: UserTags?) :
    ListAdapter<MessageUiModel, ChatAdapter.MessageViewHolder>(CustomItemCallback<MessageUiModel>()) {

    class MessageViewHolder(val binding: LayoutMessageItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder =
        MessageViewHolder(
            LayoutMessageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        user?.let {
            val message = getItem(position)
            with(holder.binding) {
                tvMessage.text = message.message
                tvDate.text = message.sentDate
                if (user == UserTags.FIRST_USER_TAG && message.isSentByFirstUser ||
                    user == UserTags.SECOND_USER_TAG && !message.isSentByFirstUser
                ) {
                    root.scaleX = 1f
                    tvMessage.scaleX = 1f
                    tvDate.scaleX = 1f
                } else {
                    root.scaleX = -1f
                    tvMessage.scaleX = -1f
                    tvDate.scaleX = -1f
                }
            }
        }
    }
}