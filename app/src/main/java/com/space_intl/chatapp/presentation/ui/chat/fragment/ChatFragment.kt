package com.space_intl.chatapp.presentation.ui.chat.fragment

import android.content.IntentFilter
import com.space_intl.chatapp.common.extensions.*
import com.space_intl.chatapp.common.util.S
import com.space_intl.chatapp.databinding.FragmentChatBinding
import com.space_intl.chatapp.presentation.base.fragment.BaseChatFragment
import com.space_intl.chatapp.presentation.base.fragment.Inflater
import com.space_intl.chatapp.presentation.ui.chat.adapter.ChatAdapter
import com.space_intl.chatapp.presentation.ui.chat.model.MessageUIModel
import com.space_intl.chatapp.presentation.ui.chat.viewmodel.ChatViewModel
import com.space_intl.chatapp.service.MessageReceiver
import com.space_intl.chatapp.service.Receiver
import kotlin.reflect.KClass

class ChatFragment : BaseChatFragment<FragmentChatBinding, ChatViewModel>() {

    private val userMessagesAdapter by lazy { ChatAdapter(listener) }

    override val filter by lazy { IntentFilter(receiver.actionName) }
    override fun setReceiver(): Receiver = lazy { MessageReceiver(fragmentActivity) }.value
    override fun inflate(): Inflater<FragmentChatBinding> = FragmentChatBinding::inflate
    override val viewModelClass: KClass<ChatViewModel> get() = ChatViewModel::class

    override fun onBindViewModel(vm: ChatViewModel) {
        vm.retrieveMessages(userId)
        listeners(vm)
        loadContent(vm)
    }

    private fun listeners(vm: ChatViewModel) {
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

    private fun resendMessage(vm: ChatViewModel, oldMessage: MessageUIModel) {
        val messageText = oldMessage.message
        vm.removeMessage(oldMessage)
        vm.sendMessage(messageText, userId, fragmentContext.isOnline())
    }

    private fun loadContent(vm: ChatViewModel) {
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

    private fun sendMessage(vm: ChatViewModel) {
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