package com.space_intl.chatapp.presentation.ui.chat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.space_intl.chatapp.R
import com.space_intl.chatapp.common.extensions.setBackgroundTint
import com.space_intl.chatapp.common.extensions.setTint
import com.space_intl.chatapp.common.extensions.toFormattedDate
import com.space_intl.chatapp.databinding.LayoutMessageItemBinding
import com.space_intl.chatapp.domain.model.MessageModel
import com.space_intl.chatapp.presentation.base.adapter.BaseAdapter

typealias C = R.color

class ChatAdapter(listener: AdapterListener) :
    BaseAdapter<MessageModel, LayoutMessageItemBinding, ChatAdapter.ChatViewHolder>(listener) {

    class ChatViewHolder(private val binding: LayoutMessageItemBinding) :
        BaseViewHolder<MessageModel, LayoutMessageItemBinding>(binding) {

        override fun onBind(item: MessageModel, listener: AdapterListener) {
            with(binding) {
                messageTextView.text = item.message
                dateTextView.text = item.dateSent.toFormattedDate()
                val isSentMessage = (listener.getUserId() == item.userId)

                setScale(
                    if (isSentMessage) 1f else -1f,
                    root, messageTextView, dateTextView
                )
                setColor(
                    if (isSentMessage) C.purple_light else C.neutral_05,
                    smallCircleImageView, bigCircleImageView, messageTextView
                )
            }
        }

        private fun setScale(scale: Float, vararg views: View) {
            views.forEach { view ->
                view.scaleX = scale
            }
        }

        private fun setColor(color: Int, vararg views: View) {
            views.forEach { view ->
                when (view) {
                    is ImageView -> view.setTint(color)
                    is TextView -> view.setBackgroundTint(color)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder =
        ChatViewHolder(
            LayoutMessageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
}