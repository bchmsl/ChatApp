package com.bchmsl.chatapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bchmsl.chatapp.R
import com.bchmsl.chatapp.common.extensions.*
import com.bchmsl.chatapp.databinding.LayoutMessageItemBinding
import com.bchmsl.chatapp.domain.model.MessageModel
import com.bchmsl.chatapp.presentation.model.UserTags

typealias C = R.color

class ChatAdapter(private val user: UserTags?) :
    ListAdapter<MessageModel, ChatAdapter.MessageViewHolder>(CustomItemCallback<MessageModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder =
        MessageViewHolder(
            LayoutMessageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(getItem(position), user)
    }

    class MessageViewHolder(private val binding: LayoutMessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: MessageModel, user: UserTags?) {
            user?.let {
                with(binding) {
                    tvMessage.text = message.message
                    tvDate.text = message.dateSent.toFormattedDate()
                    val isSentMessage = (user == message.sentBy)

                    setScale(
                        if (isSentMessage) 1f else -1f,
                        root, tvMessage, tvDate
                    )
                    setColor(
                        if (isSentMessage) C.purple_light else C.neutral_05,
                        ivCircleSmall, ivCircleBig, tvMessage
                    )
                }
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
}