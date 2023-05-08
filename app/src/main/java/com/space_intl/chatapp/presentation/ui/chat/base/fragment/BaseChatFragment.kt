package com.space_intl.chatapp.presentation.ui.chat.base.fragment

import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.space_intl.chatapp.databinding.FragmentChatBinding
import com.space_intl.chatapp.presentation.base.fragment.BaseFragment
import com.space_intl.chatapp.presentation.base.fragment.Inflater
import com.space_intl.chatapp.presentation.ui.chat.adapter.ChatAdapter
import com.space_intl.chatapp.presentation.ui.chat.model.MessageUIModel
import com.space_intl.chatapp.presentation.ui.chat.viewmodel.ChatViewModel
import com.space_intl.chatapp.service.BroadcastReceiver
import kotlin.reflect.KClass

abstract class BaseChatFragment: BaseFragment<FragmentChatBinding, ChatViewModel>(), BroadcastReceiver {

    abstract val userMessagesAdapter: ChatAdapter

    override val viewModelClass: KClass<ChatViewModel> get() = ChatViewModel::class

    override fun inflate(): Inflater<FragmentChatBinding> =
        FragmentChatBinding::inflate

    override fun onStart() {
        super.onStart()
        requireActivity().registerReceiver(receiver, filter)
    }

    override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver)
    }

    override fun onBindViewModel(vm: ChatViewModel) {
        vm.retrieveMessages(userId)
        listeners(vm)
        loadContent(vm)
    }

    abstract fun listeners(vm: ChatViewModel)

    abstract fun resendMessage(vm: ChatViewModel, oldMessage: MessageUIModel)

    abstract fun loadContent(vm: ChatViewModel)

    abstract fun sendMessage(vm: ChatViewModel)

    override fun sendBroadcast(action: String) {
        requireActivity().sendBroadcast(Intent(action))
    }
}