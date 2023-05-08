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

class ChatFragment : BaseChatFragment() {
    override val userId: String
        get() = tag.toString()

    override val receiver: Receiver
        get() = MessageReceiver(fragmentActivity)

    override val userMessagesAdapter: ChatAdapter by lazy { ChatAdapter(listener) }

    override val filter: IntentFilter by lazy{IntentFilter(receiver.actionName)}

    override fun listeners(vm: ChatViewModel) {
        with(binding) {
            sendButton.setOnClickListener {
                sendMessage(vm)
                fragmentActivity.hideKeyboard(root)
            }
            userMessagesAdapter.onItemClick {
                if (fragmentContext.isOnline()) {
                    resendMessage(vm, it)
                } else {
                    root.makeSnackbar(getString(S.check_internet_connection), true)
                }
            }
        }
    }

    override fun resendMessage(vm: ChatViewModel, oldMessage: MessageUIModel) {
        val messageText = oldMessage.message
        vm.removeMessage(oldMessage)
        vm.sendMessage(messageText, userId, fragmentContext.isOnline())
    }

    override fun loadContent(vm: ChatViewModel) {
        binding.chatRecyclerView.apply {
            adapter = userMessagesAdapter
            itemAnimator = null
        }
        collectAsync(vm.messagesHistoryState) { messages ->
            userMessagesAdapter.submitList(messages.toList())
        }
        receiver.callback = {
            vm.retrieveMessages(userId)
        }
    }

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