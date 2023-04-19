package com.bchmsl.chatapp.presentation.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bchmsl.chatapp.databinding.LayoutMessageItemBinding
import com.bchmsl.chatapp.presentation.model.MessageUiModel

class SecondUserMessagesAdapter : ListAdapter<MessageUiModel, SecondUserMessagesAdapter.ViewHolder>(itemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondUserMessagesAdapter.ViewHolder =
        ViewHolder(
            LayoutMessageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SecondUserMessagesAdapter.ViewHolder, position: Int) {
        holder.onBind()
    }

    inner class ViewHolder(private val binding: LayoutMessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            val currentItem = getItem(adapterPosition)
            with(binding) {
                tvMessage.text = currentItem.message
                tvDate.text = currentItem.sentDate
                if (currentItem.isSentByFirstUser) {
                    tvMessage.scaleX = -1f
                    root.scaleX = -1f
                    tvDate.scaleX = -1f
                } else {
                    tvMessage.scaleX = 1f
                    root.scaleX = 1f
                    tvDate.scaleX = 1f
                }
            }
        }
    }

    companion object {
        val itemCallback = object : DiffUtil.ItemCallback<MessageUiModel>() {
            override fun areItemsTheSame(
                oldItem: MessageUiModel,
                newItem: MessageUiModel
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MessageUiModel,
                newItem: MessageUiModel
            ): Boolean =
                oldItem == newItem
        }
    }
}