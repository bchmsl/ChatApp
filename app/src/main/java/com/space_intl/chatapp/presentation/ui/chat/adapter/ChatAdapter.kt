package com.space_intl.chatapp.presentation.ui.chat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space_intl.chatapp.common.util.C
import com.space_intl.chatapp.presentation.base.adapter.ViewHolderHelper
import com.space_intl.chatapp.common.util.S
import com.space_intl.chatapp.databinding.LayoutMessageItemBinding
import com.space_intl.chatapp.presentation.base.adapter.CustomItemCallback
import com.space_intl.chatapp.presentation.ui.chat.model.MessageUIModel

class ChatAdapter(private val listener: () -> String) :
    ListAdapter<MessageUIModel, ChatAdapter.ChatViewHolder>(CustomItemCallback<MessageUIModel>()) {

    private var click: ((MessageUIModel) -> Unit)? = null

    fun onItemClick(block: (MessageUIModel) -> Unit) {
        click = block
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.onBind(getItem(position), listener, click)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder =
        ChatViewHolder(
            LayoutMessageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    class ChatViewHolder(private val binding: LayoutMessageItemBinding) :
        RecyclerView.ViewHolder(binding.root), ViewHolderHelper {
        fun onBind(
            item: MessageUIModel,
            listener: () -> String,
            onItemClick: ((MessageUIModel) -> Unit)?
        ) {
            with(binding) {
                messageTextView.text = item.message
                val isSentMessage = (listener.invoke() == item.userId)
                handleDelivery(item, binding)
                handleFlip(isSentMessage, binding)

                setListener(root, item) {
                    onItemClick?.invoke(item)
                }
            }
        }

        private fun setListener(view: View, item: MessageUIModel, onItemClick: () -> Unit) {
            view.setOnClickListener {
                if (!item.isDelivered) {
                    onItemClick.invoke()
                }
            }
        }

        private fun handleFlip(isSentMessage: Boolean, binding: LayoutMessageItemBinding) {
            with(binding) {
                if (isSentMessage) {
                    setScaleX(DIRECTION_LTR, root, messageTextView, dateTextView)
                    setColor(
                        C.purple_light,
                        smallCircleImageView,
                        bigCircleImageView,
                        messageTextView
                    )
                } else {
                    setScaleX(DIRECTION_RTL, root, messageTextView, dateTextView)
                    setColor(
                        C.neutral_02_dark_grey,
                        smallCircleImageView,
                        bigCircleImageView,
                        messageTextView
                    )
                }
            }
        }

        private fun handleDelivery(item: MessageUIModel, binding: LayoutMessageItemBinding) {
            with(binding) {
                if (item.isDelivered) {
                    dateTextView.text = item.dateSentStr
                    setTextColor(C.neutral_02_dark_grey, dateTextView)
                    setAlpha(OPACITY_FULL, root)
                } else {
                    dateTextView.text = dateTextView.context.getString(S.not_delivered)
                    setTextColor(C.error_label, dateTextView)
                    setAlpha(OPACITY_HALF, root)
                }
            }
        }

        companion object {
            private const val DIRECTION_LTR = 1f
            private const val DIRECTION_RTL = -1f
            private const val OPACITY_FULL = 1f
            private const val OPACITY_HALF = 0.5f
        }
    }
}