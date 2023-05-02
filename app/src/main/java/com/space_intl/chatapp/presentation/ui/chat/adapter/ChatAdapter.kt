package com.space_intl.chatapp.presentation.ui.chat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import com.space_intl.chatapp.R
import com.space_intl.chatapp.common.extensions.setBackgroundTint
import com.space_intl.chatapp.common.extensions.setTint
import com.space_intl.chatapp.common.extensions.toFormattedDate
import com.space_intl.chatapp.databinding.LayoutMessageItemBinding
import com.space_intl.chatapp.domain.model.MessageModel
import com.space_intl.chatapp.presentation.base.adapter.AdapterListener
import com.space_intl.chatapp.presentation.base.adapter.BaseAdapter

typealias C = R.color
typealias S = R.string

class ChatAdapter(listener: AdapterListener) :
    BaseAdapter<MessageModel, LayoutMessageItemBinding, ChatAdapter.ChatViewHolder>(listener) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder =
        ChatViewHolder(
            LayoutMessageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    class ChatViewHolder(private val binding: LayoutMessageItemBinding) :
        BaseViewHolder<MessageModel, LayoutMessageItemBinding>(binding) {

        override fun onBind(item: MessageModel, listener: AdapterListener) {
            with(binding) {
                val isSentMessage = (listener.getUserId() == item.userId)

                messageTextView.text = item.message
                dateTextView.text =
                    if (item.isDelivered) item.dateSent.toFormattedDate() else dateTextView.context.getString(
                        S.not_delivered
                    )

                setScaleX(
                    if (isSentMessage) DIRECTION_LTR else DIRECTION_RTL,
                    root, messageTextView, dateTextView
                )

                setColor(
                    if (isSentMessage) COLOR_SENT else COLOR_RECEIVED,
                    smallCircleImageView, bigCircleImageView, messageTextView
                )

                setTextColor(
                    if (item.isDelivered) COLOR_DATE else COLOR_ERROR,
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
            private const val COLOR_SENT = C.purple_light
            private const val COLOR_RECEIVED = C.neutral_02
            private const val COLOR_DATE = C.neutral_02
            private const val COLOR_ERROR = C.red

        }
    }
}