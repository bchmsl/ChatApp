package com.bchmsl.chatapp.presentation.user

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MessagesReceiver: BroadcastReceiver() {
    companion object{
        const val ACTION = "MESSAGE_RECEIVED"
    }
    var callback: (() -> Unit)? = null
    override fun onReceive(context: Context?, intent: Intent?) {
        callback?.invoke()
    }
}