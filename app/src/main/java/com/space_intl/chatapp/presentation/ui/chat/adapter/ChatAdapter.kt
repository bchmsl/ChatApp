package com.space_intl.chatapp.presentation.ui.chat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space_intl.chatapp.common.util.C
import com.space_intl.chatapp.common.util.S
import com.space_intl.chatapp.databinding.ItemMessageBinding
import com.space_intl.chatapp.presentation.base.adapter.AdapterFlipper
import com.space_intl.chatapp.presentation.base.adapter.CustomItemCallback
import com.space_intl.chatapp.presentation.base.adapter.OnClickListener
import com.space_intl.chatapp.presentation.base.adapter.ViewHolderHelper
import com.space_intl.chatapp.presentation.ui.chat.adapter.ChatAdapter.ChatViewHolder
import com.space_intl.chatapp.presentation.ui.chat.model.MessageUIModel

/**
 * RecyclerView Adapter for [ChatFragment].
 * @param flipper as a parameter to handle message flip in the RecyclerView.
 * @see ListAdapter
 * @see ChatViewHolder
 * @see CustomItemCallback
 */
class ChatAdapter(
    private val flipper: AdapterFlipper<String>,
) : ListAdapter<MessageUIModel, ChatViewHolder>(CustomItemCallback<MessageUIModel>()) {

    private var listener: OnClickListener<MessageUIModel>? = null

    fun setListener(listener: OnClickListener<MessageUIModel>) {
        this.listener = listener
    }

    /**
     * Function to bind the data to the view.
     * @param holder as a parameter for the view holder.
     * @param position as a parameter for the position of the item.
     * @see ListAdapter.onBindViewHolder
     */

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.onBind(getItem(position), flipper, listener)
    }

    /**
     * Function to create the view holder.
     * @param parent as a parameter to create the view holder.
     * @param viewType as a parameter to create the view holder.
     * @return the created view holder.
     * @see ListAdapter.onCreateViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder =
        ChatViewHolder(
            ItemMessageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    /**
     * ViewHolder class for the [ChatAdapter].
     * @param binding as a parameter to bind the data to the view.
     * @see RecyclerView.ViewHolder
     * @see ViewHolderHelper
     */
    class ChatViewHolder(private val binding: ItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root), ViewHolderHelper {

        /**
         * Function to bind the data to the view.
         * @param item as a parameter to bind the data to the view.
         * @param flipper as a parameter to handle message flip in the RecyclerView.
         * @param onItemClick as a parameter to handle item click.
         */
        fun onBind(
            item: MessageUIModel,
            flipper: AdapterFlipper<String>,
            listener: OnClickListener<MessageUIModel>?
        ) {
            with(binding) {
                messageTextView.text = item.message
                val isSentMessage = (flipper.userId() == item.userId)
                handleDelivery(item, binding)
                handleFlip(isSentMessage, binding)

                setListener(root, item) {
                    listener?.onClick(item, adapterPosition)
                }
            }
        }

        /**
         * Function to set the listener to the view.
         * @param view as a parameter to set the listener to the view.
         * @param item as a parameter to handle message flip in the RecyclerView.
         * @param onItemClick as a parameter to handle item click.
         * @see View.setOnClickListener
         * @see ChatAdapter.onItemClick
         */
        private fun setListener(view: View, item: MessageUIModel, onItemClick: () -> Unit) {
            view.setOnClickListener {
                if (!item.isDelivered) {
                    onItemClick()
                }
            }
        }

        /**
         * Function to handle message flip in the RecyclerView.
         * @param isSentMessage as a parameter to handle message flip in the RecyclerView.
         * @param binding as a parameter to handle message flip in the RecyclerView.
         * @see ViewHolderHelper.setScaleX
         * @see ViewHolderHelper.setColor
         */
        private fun handleFlip(isSentMessage: Boolean, binding: ItemMessageBinding) {
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

        /**
         * Function to handle message delivery.
         * @param item as a parameter to handle message delivery.
         * @param binding as a parameter to handle message delivery.
         * @see ViewHolderHelper.setTextColor
         * @see ViewHolderHelper.setAlpha
         */
        private fun handleDelivery(item: MessageUIModel, binding: ItemMessageBinding) {
            with(binding) {
                if (item.isDelivered) {
                    dateTextView.text = item.dateSentStr
                    setTextColor(C.neutral_02_dark_grey, dateTextView)
                    setAlpha(OPACITY_FULL, root)
                } else {
                    dateTextView.text = dateTextView.context.getString(S.not_delivered).uppercase()
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
