package com.bchmsl.chatapp.presentation.ui.chat

import android.content.ContentValues.TAG
import android.content.IntentFilter
import android.util.Log
import com.bchmsl.chatapp.common.extensions.collectAsync
import com.bchmsl.chatapp.common.extensions.hideKeyboard
import com.bchmsl.chatapp.databinding.FragmentChatBinding
import com.bchmsl.chatapp.presentation.adapter.ChatAdapter
import com.bchmsl.chatapp.presentation.base.BaseFragment
import com.bchmsl.chatapp.presentation.base.Inflater
import com.bchmsl.chatapp.presentation.model.MessageUiModel
import com.bchmsl.chatapp.presentation.model.UserTags
import com.bchmsl.chatapp.service.MessageReceiver
import com.bchmsl.chatapp.service.Receiver
import kotlinx.coroutines.delay

class ChatFragment : BaseFragment<FragmentChatBinding, UserViewModel>() {

    private val userMessagesAdapter by lazy { ChatAdapter(tag?.let { UserTags.valueOf(it) }) }
    override val filter by lazy { IntentFilter(receiver?.actionName) }
    override fun setReceiver(): Receiver = lazy { MessageReceiver(requireActivity()) }.value
    override fun inflate(): Inflater<FragmentChatBinding> = FragmentChatBinding::inflate
    override fun provideViewModel(): Class<UserViewModel> = UserViewModel::class.java

    override fun onBind() {

    }

    override fun onBindViewModel(vm: UserViewModel) {
        listeners(vm)
        loadContent(vm)
        vm.retrieveMessages()
    }

    private fun listeners(vm: UserViewModel) {
        binding.btnSend.setOnClickListener {
            sendMessage(vm)
            requireActivity().hideKeyboard(binding.root)
        }
    }

    private fun loadContent(vm: UserViewModel) {
        binding.rvMessageHistory.adapter = userMessagesAdapter
        collectAsync(vm.messagesHistoryState) {
            Log.d(TAG, "loadContent:")
            userMessagesAdapter.submitList(MessageUiModel.messagesTestList.toList())
            delay(DELAY)
            binding.rvMessageHistory.smoothScrollToPosition(userMessagesAdapter.itemCount - 1)
        }
        receiver?.callback = {
            vm.retrieveMessages()
        }
    }

    private fun sendMessage(vm: UserViewModel) {
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