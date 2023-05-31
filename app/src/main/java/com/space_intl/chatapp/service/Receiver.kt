package com.space_intl.chatapp.service

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Receiver for broadcast messages.
 * @param activity The activity where the receiver is registered.
 * @see BroadcastReceiver
 */
abstract class Receiver(private val activity: Activity) : BroadcastReceiver() {
    var callback: ((Int) -> Unit)? = null
    abstract val actionName: String
    abstract val intent: Intent
    abstract val extraName: String
    override fun onReceive(context: Context?, intent: Intent?) {
        val id = intent?.getIntExtra(extraName, 0) ?: 0
        callback?.invoke(id)
    }

    fun sendBroadcast(action: String, messageId: Int) {
        intent.action = action
        intent.putExtra(extraName, messageId)
        activity.sendBroadcast(intent)
    }
}
