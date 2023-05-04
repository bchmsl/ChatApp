package com.space_intl.chatapp.presentation.ui.chat.fragment

import android.content.IntentFilter
import com.space_intl.chatapp.common.extensions.collectAsync
import com.space_intl.chatapp.common.extensions.hideKeyboard
import com.space_intl.chatapp.common.extensions.isOnline
import com.space_intl.chatapp.databinding.FragmentChatBinding
import com.space_intl.chatapp.presentation.base.fragment.BaseChatFragment
import com.space_intl.chatapp.presentation.base.fragment.Inflater
import com.space_intl.chatapp.presentation.ui.chat.adapter.ChatAdapter
import com.space_intl.chatapp.presentation.ui.chat.model.MessageUiModel
import com.space_intl.chatapp.presentation.ui.chat.viewmodel.ChatViewModel
import com.space_intl.chatapp.service.MessageReceiver
import com.space_intl.chatapp.service.Receiver
import kotlin.reflect.KClass


class ChatFragment : BaseChatFragment<FragmentChatBinding, ChatViewModel>() {

    private val userMessagesAdapter by lazy { ChatAdapter(listener) }

    override val filter by lazy { IntentFilter(receiver.actionName) }
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
        binding.chatRecyclerView.apply {
            adapter = userMessagesAdapter
        }
        collectAsync(vm.messagesHistoryState) { messages ->
            userMessagesAdapter.submitList(filterMessages(messages).toList())
        }
        collectAsync(vm.messageState) {
            binding.messageEditText.setText(it)
        }

        receiver.callback = {
            vm.retrieveMessages()
        }
    }

    private fun filterMessages(messages: List<MessageUiModel>): List<MessageUiModel> {
        return messages.filter {
            val isMessageSent = it.userId == userId
            val isMessageShown = (!it.isDelivered && isMessageSent) || it.isDelivered
            isMessageShown
        }
    }

    private fun sendMessage(vm: ChatViewModel) {
        with(binding) {
            messageEditText.text?.let {
                vm.sendMessage(
                    it.toString(),
                    userId,
                    requireContext().isOnline()
                )
            }
            messageEditText.setText("")
            collectAsync(vm.messageSentState) { messageSent ->
                if (messageSent) {
                    sendBroadcast(receiver.actionName)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        vm.saveMessageState(binding.messageEditText.text.toString())
    }
    companion object {
        private const val DELAY = 1000L
    }
}