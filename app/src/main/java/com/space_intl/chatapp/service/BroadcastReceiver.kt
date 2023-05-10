package com.space_intl.chatapp.service

import android.content.IntentFilter

/**
 * Interface for broadcast receiver. It provides helper methods that facilitate
 * sending of broadcast messages.
 * @see Receiver
 * @see IntentFilter
 */
interface BroadcastReceiver {
    val receiver: Receiver
    val filter: IntentFilter
    fun sendBroadcast(action: String)
}
