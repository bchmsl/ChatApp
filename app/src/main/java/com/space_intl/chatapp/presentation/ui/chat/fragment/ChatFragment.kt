package com.space_intl.chatapp.presentation.ui.chat.fragment

import com.space_intl.chatapp.presentation.ui.chat.adapter.ChatAdapter
import com.space_intl.chatapp.presentation.ui.chat.base.fragment.BaseChatFragment
import com.space_intl.chatapp.presentation.ui.chat.viewmodel.ChatViewModel

/**
 * Fragment for Chat.
 * @see BaseChatFragment
 * @see ChatViewModel
 * @see ChatAdapter
 */
class ChatFragment : BaseChatFragment() {
    override fun userId(): String {
        return tag.toString()
    }
}
