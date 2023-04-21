package com.bchmsl.chatapp.presentation.ui.chat

import android.content.IntentFilter
import com.bchmsl.chatapp.common.extensions.async
import com.bchmsl.chatapp.common.extensions.getCurrentDateTime
import com.bchmsl.chatapp.databinding.FragmentUserBinding
import com.bchmsl.chatapp.presentation.adapter.ChatAdapter
import com.bchmsl.chatapp.presentation.base.BaseFragment
import com.bchmsl.chatapp.presentation.base.Inflater
import com.bchmsl.chatapp.presentation.model.UserTags
import com.bchmsl.chatapp.presentation.model.MessageUiModel
import com.bchmsl.chatapp.service.MessagesReceiver

class ChatFragment : BaseFragment<FragmentUserBinding, UserViewModel, MessagesReceiver>() {

    private val userMessagesAdapter by lazy { ChatAdapter(tag?.let { UserTags.valueOf(it) }) }
    override val filter = IntentFilter(MessagesReceiver.ACTION)
    override fun setReceiver(): MessagesReceiver = lazy { MessagesReceiver() }.value
    override fun inflate(): Inflater<FragmentUserBinding> = FragmentUserBinding::inflate
    override fun provideViewModel(): Class<UserViewModel> = UserViewModel::class.java


    override fun listeners(vm: UserViewModel) {
        binding.btnSend.setOnClickListener {
            sendMessage(vm)
        }
    }

    override fun loadContent(vm: UserViewModel) {
        binding.rvMessageHistory.adapter = userMessagesAdapter
        async {
            vm.messagesHistoryState.collect {
                userMessagesAdapter.submitList(MessageUiModel.messagesTestList.toList())
                binding.rvMessageHistory.scrollToPosition(userMessagesAdapter.itemCount - 1)
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
                    requireContext().getCurrentDateTime(),
                    tag == UserTags.FIRST_USER_TAG.name
                )
                etMessage.setText("")
                sendBroadcast(MessagesReceiver.ACTION)
            }
        }
    }
}