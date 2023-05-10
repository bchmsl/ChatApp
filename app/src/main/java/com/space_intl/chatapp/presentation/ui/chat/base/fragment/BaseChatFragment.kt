package com.space_intl.chatapp.presentation.ui.chat.base.fragment

import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.space_intl.chatapp.common.extensions.*
import com.space_intl.chatapp.common.util.S
import com.space_intl.chatapp.databinding.FragmentChatBinding
import com.space_intl.chatapp.presentation.base.fragment.BaseFragment
import com.space_intl.chatapp.presentation.base.fragment.Inflater
import com.space_intl.chatapp.presentation.ui.chat.adapter.ChatAdapter
import com.space_intl.chatapp.presentation.ui.chat.model.MessageUIModel
import com.space_intl.chatapp.presentation.ui.chat.viewmodel.ChatViewModel
import com.space_intl.chatapp.service.BroadcastReceiver
import com.space_intl.chatapp.service.MessageReceiver
import com.space_intl.chatapp.service.Receiver
import kotlin.reflect.KClass

/**
 * Base fragment for Chat Fragments.
 * Implements [BroadcastReceiver] to send broadcast messages.
 * @see BaseFragment
 * @see BroadcastReceiver
 */
open class BaseChatFragment :
    BaseFragment<FragmentChatBinding, ChatViewModel>(), BroadcastReceiver {

    override val viewModelClass: KClass<ChatViewModel> get() = ChatViewModel::class
    override val receiver: Receiver get() = MessageReceiver(fragmentActivity)
    private val userMessagesAdapter: ChatAdapter by lazy { ChatAdapter(listener) }
    override val filter: IntentFilter by lazy { IntentFilter(receiver.actionName) }
    override fun inflate(): Inflater<FragmentChatBinding> = FragmentChatBinding::inflate

    protected val userId: String get() = userId()
    open fun userId(): String = userId()

    private val listener = {
        userId
    }


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

    private fun listeners(vm: ChatViewModel) {
        with(binding) {

            // Send message when the user clicks on the send button.
            sendButton.setOnClickListener {
                sendMessage(vm)
                fragmentActivity.hideKeyboard(root)
            }

            // Resend not delivered message when the user clicks on it.
            userMessagesAdapter.onItemClick {
                if (fragmentContext.isOnline()) {
                    resendMessage(vm, it)
                } else {
                    root.makeSnackbar(getString(S.check_internet_connection), true)
                }
            }
        }
    }

    /**
     * Resends a message.
     * @param vm The view model of the fragment.
     * @param oldMessage The message to be resent.
     * @see ChatViewModel.removeMessage
     * @see ChatViewModel.sendMessage
     */
    private fun resendMessage(vm: ChatViewModel, oldMessage: MessageUIModel) {
        val messageText = oldMessage.message
        vm.removeMessage(oldMessage)
        vm.sendMessage(messageText, userId, fragmentContext.isOnline())
    }

    /**
     * Loads the content of the fragment.
     * Is the first method to be called when the fragment view is created.
     * @param vm The view model of the fragment.
     */
    private fun loadContent(vm: ChatViewModel) {

        // Set the adapter of the recycler view.
        binding.chatRecyclerView.apply {
            adapter = userMessagesAdapter
            itemAnimator = null
        }

        // Collect the messages from the view model and submit them to the adapter.
        collectAsync(vm.messagesHistoryState) { messages ->
            userMessagesAdapter.submitList(messages.toList())
        }

        // Adapter callback for the item click.
        receiver.callback = {
            vm.retrieveMessages(userId)
        }
    }

    /**
     * Sends a message.
     * @param vm The view model of the fragment.
     * @see ChatViewModel.sendMessage
     */
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

    override fun sendBroadcast(action: String) {
        receiver.sendBroadcast(action)
    }
}
