package com.bchmsl.chatapp.presentation.ui.chat

import android.content.ContentValues.TAG
import android.content.IntentFilter
import android.util.Log
import com.bchmsl.chatapp.common.extensions.collectAsync
import com.bchmsl.chatapp.common.extensions.hideKeyboard
import com.bchmsl.chatapp.databinding.FragmentChatBinding
import com.bchmsl.chatapp.domain.model.MessageModel
import com.bchmsl.chatapp.presentation.adapter.ChatAdapter
import com.bchmsl.chatapp.presentation.base.BaseFragment
import com.bchmsl.chatapp.presentation.base.Inflater
import com.bchmsl.chatapp.presentation.model.UserTags
import com.bchmsl.chatapp.service.MessageReceiver
import com.bchmsl.chatapp.service.Receiver
import kotlinx.coroutines.delay
import kotlin.reflect.KClass

class ChatFragment : BaseFragment<FragmentChatBinding, ChatViewModel>() {
    private val userMessagesAdapter by lazy { ChatAdapter(tag?.let { UserTags.valueOf(it) }) }
    override val filter by lazy { IntentFilter(receiver?.actionName) }
    override fun setReceiver(): Receiver = lazy { MessageReceiver(requireActivity()) }.value
    override fun inflate(): Inflater<FragmentChatBinding> = FragmentChatBinding::inflate

    override val vmc: KClass<ChatViewModel> get() = ChatViewModel::class

    override fun onBindViewModel(vm: ChatViewModel) {
        listeners(vm)
        loadContent(vm)
        vm.retrieveMessages()
    }

    private fun listeners(vm: ChatViewModel) {
        binding.btnSend.setOnClickListener {
            sendMessage(vm)
            requireActivity().hideKeyboard(binding.root)
        }
    }

    private fun loadContent(vm: ChatViewModel) {
        binding.rvMessageHistory.adapter = userMessagesAdapter
        collectAsync(vm.messagesHistoryState) {
            Log.d(TAG, "loadContent:")
            userMessagesAdapter.submitList(MessageModel.messagesTestList.toList())
            delay(DELAY)
            binding.rvMessageHistory.smoothScrollToPosition(userMessagesAdapter.itemCount - 1)
        }
        receiver?.callback = {
            vm.retrieveMessages()
        }
    }

    private fun sendMessage(vm: ChatViewModel) {
        with(binding) {
            etMessage.text?.let {
                vm.sendMessage(
                    it,
                    UserTags.valueOf(tag ?: "")
                )
            }
            etMessage.setText("")
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