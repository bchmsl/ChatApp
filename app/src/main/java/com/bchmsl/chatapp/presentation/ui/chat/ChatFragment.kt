package com.bchmsl.chatapp.presentation.ui.chat

import android.content.IntentFilter
import com.bchmsl.chatapp.common.extensions.executeAsync
import com.bchmsl.chatapp.common.extensions.hideKeyboard
import com.bchmsl.chatapp.databinding.FragmentChatBinding
import com.bchmsl.chatapp.presentation.adapter.ChatAdapter
import com.bchmsl.chatapp.presentation.base.BaseFragment
import com.bchmsl.chatapp.presentation.base.Inflater
import com.bchmsl.chatapp.presentation.model.MessageUiModel
import com.bchmsl.chatapp.presentation.model.UserTags
import com.bchmsl.chatapp.service.MessagesReceiver
import kotlinx.coroutines.delay
import java.util.*

class ChatFragment : BaseFragment<FragmentChatBinding, UserViewModel, MessagesReceiver>() {

    private val userMessagesAdapter by lazy { ChatAdapter(tag?.let { UserTags.valueOf(it) }) }
    override val filter = IntentFilter(MessagesReceiver.ACTION)
    override fun setReceiver(): MessagesReceiver = lazy { MessagesReceiver() }.value
    override fun inflate(): Inflater<FragmentChatBinding> = FragmentChatBinding::inflate
    override fun provideViewModel(): Class<UserViewModel> = UserViewModel::class.java

    override fun onBind() {

    }

    override fun onBindViewModel(vm: UserViewModel) {
        listeners(vm)
        loadContent(vm)
    }

    private fun listeners(vm: UserViewModel) {
        binding.btnSend.setOnClickListener {
            sendMessage(vm)
            requireActivity().hideKeyboard(binding.root)
        }
    }

    private fun loadContent(vm: UserViewModel) {
        vm.retrieveMessages()
        binding.rvMessageHistory.adapter = userMessagesAdapter
        executeAsync {
            vm.messagesHistoryState.collect {
                userMessagesAdapter.submitList(MessageUiModel.messagesTestList.toList())
                delay(100)
                binding.rvMessageHistory.smoothScrollToPosition(userMessagesAdapter.itemCount - 1)
            }
        }
        receiver.callback = {
            vm.retrieveMessages()
        }
    }

    private fun sendMessage(vm: UserViewModel) {
        with(binding) {
            if (!etMessage.text.isNullOrBlank()) {
                vm.sendMessage(
                    etMessage.text.toString(),
                    Calendar.getInstance().timeInMillis,
                    tag == UserTags.FirstUser.name
                )
                etMessage.setText("")
                sendBroadcast(MessagesReceiver.ACTION)
            }
        }
    }
}