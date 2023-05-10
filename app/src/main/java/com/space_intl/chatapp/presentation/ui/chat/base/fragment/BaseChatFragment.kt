package com.space_intl.chatapp.presentation.ui.chat.base.fragment

import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.space_intl.chatapp.databinding.FragmentChatBinding
import com.space_intl.chatapp.presentation.base.fragment.BaseFragment
import com.space_intl.chatapp.presentation.base.fragment.Inflater
import com.space_intl.chatapp.presentation.ui.chat.adapter.ChatAdapter
import com.space_intl.chatapp.presentation.ui.chat.model.MessageUIModel
import com.space_intl.chatapp.presentation.ui.chat.viewmodel.ChatViewModel
import com.space_intl.chatapp.service.BroadcastReceiver
import kotlin.reflect.KClass

/**
 * Base fragment for Chat Fragments.
 * Implements [BroadcastReceiver] to send broadcast messages.
 * @see BaseFragment
 * @see BroadcastReceiver
 */
abstract class BaseChatFragment :
    BaseFragment<FragmentChatBinding, ChatViewModel>(), BroadcastReceiver {

    abstract val userMessagesAdapter: ChatAdapter
    override val viewModelClass: KClass<ChatViewModel> get() = ChatViewModel::class

    protected abstract val userId: String
    protected val listener = {
        userId
    }

    override fun inflate(): Inflater<FragmentChatBinding> =
        FragmentChatBinding::inflate

    override fun onStart() {
        super.onStart()

        // Register the receiver to the local broadcast manager.
        requireActivity().registerReceiver(receiver, filter)
    }

    override fun onStop() {
        super.onStop()

        // Unregister the receiver from the local broadcast manager.
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver)
    }

    override fun onBindViewModel(vm: ChatViewModel) {
        vm.retrieveMessages(userId)
        loadContent(vm)
        listeners(vm)
    }

    abstract fun listeners(vm: ChatViewModel)

    abstract fun resendMessage(vm: ChatViewModel, oldMessage: MessageUIModel)

    abstract fun loadContent(vm: ChatViewModel)

    abstract fun sendMessage(vm: ChatViewModel)

    override fun sendBroadcast(action: String) {
        receiver.sendBroadcast(action)
    }
}
