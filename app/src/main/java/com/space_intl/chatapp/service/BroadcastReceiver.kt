package com.space_intl.chatapp.service

import android.content.IntentFilter

interface BroadcastReceiver {
    val receiver: Receiver
    val filter: IntentFilter
    fun sendBroadcast(action: String)
}