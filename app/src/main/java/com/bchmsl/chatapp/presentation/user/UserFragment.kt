package com.bchmsl.chatapp.presentation.user

import android.content.Intent
import android.content.IntentFilter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bchmsl.chatapp.common.extensions.getCurrentDateTime
import com.bchmsl.chatapp.databinding.FragmentUserBinding
import com.bchmsl.chatapp.presentation.MainActivity
import com.bchmsl.chatapp.presentation.base.BaseFragment
import com.bchmsl.chatapp.presentation.base.Inflater
import com.bchmsl.chatapp.presentation.model.MessageUiModel
import kotlinx.coroutines.launch

class UserFragment : BaseFragment<FragmentUserBinding>() {

    private val vm: UserViewModel by viewModels()
    private val userMessagesAdapter by lazy { if (tag == MainActivity.FIRST_USER_TAG) FirstUserMessagesAdapter() else SecondUserMessagesAdapter() }
    private val receiver by lazy { MessagesReceiver() }
    private val intent by lazy { Intent() }


    override fun inflate(): Inflater<FragmentUserBinding> =
        FragmentUserBinding::inflate

    override fun listeners() {
        binding.btnSend.setOnClickListener {
            sendMessage()
        }
    }

    override fun loadContent() {
        binding.rvMessageHistory.adapter = userMessagesAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                vm.messagesHistoryState.collect {
                    userMessagesAdapter.submitList(MessageUiModel.messagesTestList.toList())
                    binding.rvMessageHistory.scrollToPosition(MessageUiModel.messagesTestList.lastIndex)
                }
            }
        }

        val filter = IntentFilter(MessagesReceiver.ACTION)
        requireContext().registerReceiver(receiver, filter)

        receiver.callback = {
            vm.retrieveMessages()
        }

    }

    private fun sendMessage() {
        with(binding) {
            if (!etMessage.text.isNullOrBlank()) {
                MessageUiModel.id += 1
                val id = MessageUiModel.id
                val messageBody = binding.etMessage.text.toString()
                val date = requireContext().getCurrentDateTime()
                val message =
                    MessageUiModel(id, messageBody, date, tag == MainActivity.FIRST_USER_TAG)
                vm.sendMessage(message)
                etMessage.setText("")


                intent.action = MessagesReceiver.ACTION
                requireActivity().sendBroadcast(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireContext().unregisterReceiver(receiver)
    }
}