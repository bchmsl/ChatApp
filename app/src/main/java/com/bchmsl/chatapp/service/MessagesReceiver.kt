package com.bchmsl.chatapp.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MessagesReceiver : BroadcastReceiver() {
    var callback: (() -> Unit)? = null
    override fun onReceive(context: Context?, intent: Intent?) {
        callback?.invoke()
    }

    companion object {
        const val ACTION = "MESSAGE_RECEIVED"
    }
}