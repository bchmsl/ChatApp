package com.space_intl.chatapp.service

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

abstract class Receiver(private val activity: Activity) : BroadcastReceiver() {
    var callback: (() -> Unit)? = null
    abstract val actionName: String
    abstract val intent: Intent
    override fun onReceive(context: Context?, intent: Intent?) {
        callback?.invoke()
    }

    fun sendBroadcast(action: String){
        intent.action = action
        activity.sendBroadcast(intent)
    }
}