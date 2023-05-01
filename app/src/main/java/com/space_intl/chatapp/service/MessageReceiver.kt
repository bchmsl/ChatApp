package com.space_intl.chatapp.service

import android.app.Activity
import android.content.Intent

class MessageReceiver(activity: Activity) : Receiver(activity) {
    override val actionName: String
        get() = "MESSAGE_SENT"

    override val intent: Intent by lazy { Intent() }
}