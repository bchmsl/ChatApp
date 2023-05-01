package com.bchmsl.chatapp.presentation.ui.chat

import android.content.ContentValues.TAG
import android.text.Editable
import android.util.Log
import androidx.lifecycle.ViewModel
import com.bchmsl.chatapp.common.extensions.executeAsync
import com.bchmsl.chatapp.presentation.model.MessageUiModel
import com.bchmsl.chatapp.presentation.model.UserTags
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import java.util.*

class UserViewModel : ViewModel() {

    private val _messagesHistoryState = MutableSharedFlow<List<MessageUiModel>>()
    val messagesHistoryState get() = _messagesHistoryState.asSharedFlow()

    private val _messageSentState = MutableStateFlow(false)
    val messageSentState get() = _messageSentState

    fun retrieveMessages() {
        executeAsync {
            // Test to get messages
            val messages = MessageUiModel.messagesTestList

            _messagesHistoryState.emit(messages)
        }
        Log.d(TAG, "retrieveMessages")
    }

    fun sendMessage(etMessage: Editable, user: UserTags) {
        // Test to get messages
        executeAsync {
            if (etMessage.toString().isNotBlank()) {
                MessageUiModel.id++
                val message = MessageUiModel(
                    MessageUiModel.id,
                    etMessage.toString(),
                    Calendar.getInstance().timeInMillis,
                    user
                )
                MessageUiModel.messagesTestList.add(message)
                _messageSentState.emit(true)
            }
        }
    }
}