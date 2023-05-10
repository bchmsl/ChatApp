package com.space_intl.chatapp.presentation.ui.chat.fragment

import android.content.IntentFilter
import com.space_intl.chatapp.common.extensions.*
import com.space_intl.chatapp.common.util.S
import com.space_intl.chatapp.presentation.ui.chat.adapter.ChatAdapter
import com.space_intl.chatapp.presentation.ui.chat.base.fragment.BaseChatFragment
import com.space_intl.chatapp.presentation.ui.chat.model.MessageUIModel
import com.space_intl.chatapp.presentation.ui.chat.viewmodel.ChatViewModel
import com.space_intl.chatapp.service.MessageReceiver
import com.space_intl.chatapp.service.Receiver

/**
 * Fragment for Chat.
 * @see BaseChatFragment
 * @see ChatViewModel
 * @see ChatAdapter
 */
class ChatFragment : BaseChatFragment() {

    /**
     * The user id of the sender of the messages.
     */
    override val userId: String
        get() = tag.toString()

    /**
     * The receiver of the broadcast messages.
     * @see MessageReceiver
     */
    override val receiver: Receiver
        get() = MessageReceiver(fragmentActivity)

    /**
     * The adapter of the recycler view.
     * @see ChatAdapter
     */
    override val userMessagesAdapter: ChatAdapter by lazy { ChatAdapter(listener) }

    /**
     * The intent filter of the broadcast messages.
     * @see IntentFilter
     */
    override val filter: IntentFilter by lazy { IntentFilter(receiver.actionName) }

    override fun listeners(vm: ChatViewModel) {
        with(binding) {

            // Send message when the user clicks on the send button.
            sendButton.setOnClickListener {
                sendMessage(vm)
                fragmentActivity.hideKeyboard(root)
            }

            // Resend not delivered message when the user clicks on it.
            userMessagesAdapter.onItemClick {
                if (fragmentContext.isOnline()) {
                    resendMessage(vm, it)
                } else {
                    root.makeSnackbar(getString(S.check_internet_connection), true)
                }
            }
        }
    }

    /**
     * Resends a message.
     * @param vm The view model of the fragment.
     * @param oldMessage The message to be resent.
     * @see ChatViewModel.removeMessage
     * @see ChatViewModel.sendMessage
     */
    override fun resendMessage(vm: ChatViewModel, oldMessage: MessageUIModel) {
        val messageText = oldMessage.message
        vm.removeMessage(oldMessage)
        vm.sendMessage(messageText, userId, fragmentContext.isOnline())
    }

    /**
     * Loads the content of the fragment.
     * Is the first method to be called when the fragment view is created.
     * @param vm The view model of the fragment.
     */
    override fun loadContent(vm: ChatViewModel) {

        // Set the adapter of the recycler view.
        binding.chatRecyclerView.apply {
            adapter = userMessagesAdapter
            itemAnimator = null
        }

        // Collect the messages from the view model and submit them to the adapter.
        collectAsync(vm.messagesHistoryState) { messages ->
            userMessagesAdapter.submitList(messages.toList())
        }

        // Adapter callback for the item click.
        receiver.callback = {
            vm.retrieveMessages(userId)
        }
    }

    /**
     * Sends a message.
     * @param vm The view model of the fragment.
     * @see ChatViewModel.sendMessage
     */
    override fun sendMessage(vm: ChatViewModel) {
        with(binding) {
            messageEditText.text?.let {
                vm.sendMessage(
                    it.toString(),
                    userId,
                    fragmentContext.isOnline()
                )
            }
            messageEditText.setEmpty()
            collectAsync(vm.messageSentState) { messageSent ->
                if (messageSent) {
                    sendBroadcast(receiver.actionName)
                }
            }
        }
    }
}
