package com.space_intl.chatapp.presentation.ui.chat.fragment

import android.content.IntentFilter
import com.space_intl.chatapp.common.extensions.collectAsync
import com.space_intl.chatapp.common.extensions.hideKeyboard
import com.space_intl.chatapp.common.extensions.scrollToBottom
import com.space_intl.chatapp.databinding.FragmentChatBinding
import com.space_intl.chatapp.presentation.base.fragment.BaseFragment
import com.space_intl.chatapp.presentation.base.fragment.Inflater
import com.space_intl.chatapp.presentation.ui.chat.vm.ChatViewModel
import com.space_intl.chatapp.presentation.ui.chat.adapter.ChatAdapter
import com.space_intl.chatapp.service.MessageReceiver
import com.space_intl.chatapp.service.Receiver
import kotlinx.coroutines.delay
import kotlin.reflect.KClass

class ChatFragment : BaseFragment<FragmentChatBinding, ChatViewModel>() {

    private val userMessagesAdapter by lazy { ChatAdapter(listener) }

    override val filter by lazy { IntentFilter(receiver?.actionName) }
    override fun setReceiver(): Receiver = lazy { MessageReceiver(requireActivity()) }.value
    override fun inflate(): Inflater<FragmentChatBinding> = FragmentChatBinding::inflate
    override val viewModelClass: KClass<ChatViewModel> get() = ChatViewModel::class

    override fun onBindViewModel(vm: ChatViewModel) {
        vm.retrieveMessages()
        listeners(vm)
        loadContent(vm)
    }

    private fun listeners(vm: ChatViewModel) {
        binding.sendButton.setOnClickListener {
            sendMessage(vm)
            requireActivity().hideKeyboard(binding.root)
        }
    }

    private fun loadContent(vm: ChatViewModel) {
        binding.chatRecyclerView.adapter = userMessagesAdapter
        collectAsync(vm.messagesHistoryState) { messages ->
            userMessagesAdapter.submitList(messages.toList())
            delay(DELAY)
            binding.chatRecyclerView.scrollToBottom()
        }
        receiver?.callback = {
            vm.retrieveMessages()
        }
    }

    private fun sendMessage(vm: ChatViewModel) {
        with(binding) {
            messageEditText.text?.let {
                vm.sendMessage(
                    it.toString(),
                    userId
                )
            }
            messageEditText.setText("")
            collectAsync(vm.messageSentState) { messageSent ->
                if (messageSent) {
                    receiver?.let { sendBroadcast(it.actionName) }
                }
            }
        }
    }

    companion object {
        private const val DELAY = 100L
    }
}