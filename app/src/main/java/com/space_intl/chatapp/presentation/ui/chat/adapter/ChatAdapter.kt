package com.space_intl.chatapp.presentation.ui.chat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space_intl.chatapp.common.extensions.setBackgroundTint
import com.space_intl.chatapp.common.extensions.setTint
import com.space_intl.chatapp.common.util.C
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
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(
            item: MessageUIModel,
            listener: () -> String,
            onItemClick: ((MessageUIModel) -> Unit)?
        ) {
            with(binding) {
                messageTextView.text = item.message
                val isSentMessage = (listener.invoke() == item.userId)
                root.setOnClickListener {
                    if (!item.isDelivered){
                        onItemClick?.invoke(item)
                    }
                }
                messageTextView.text = item.message
                dateTextView.text =
                    if (item.isDelivered) item.dateSentStr else dateTextView.context.getString(
                        S.not_delivered
                    )

                setScaleX(
                    if (isSentMessage) DIRECTION_LTR else DIRECTION_RTL,
                    root, messageTextView, dateTextView
                )

                setColor(
                    if (isSentMessage) C.purple_light else C.neutral_02_dark_grey,
                    smallCircleImageView, bigCircleImageView, messageTextView
                )

                setTextColor(
                    if (item.isDelivered) C.neutral_02_dark_grey else C.error_label,
                    dateTextView
                )

                setAlpha(
                    if (item.isDelivered) OPACITY_FULL else OPACITY_HALF,
                    root
                )
            }
        }

        private fun setScaleX(scale: Float, vararg views: View) {
            views.forEach { v ->
                v.scaleX = scale
            }
        }

        private fun setAlpha(alpha: Float, vararg views: View) {
            views.forEach { v ->
                v.alpha = alpha
            }
        }

        private fun setColor(@ColorRes color: Int, vararg views: View) {
            views.forEach { v ->
                when (v) {
                    is TextView -> v.setBackgroundTint(color)
                    is ImageView -> v.setTint(color)
                }
            }
        }

        private fun setTextColor(@ColorRes color: Int, vararg textViews: TextView) {
            textViews.forEach { t ->
                t.setTextColor(t.context.getColor(color))
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