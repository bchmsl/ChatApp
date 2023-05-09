package com.space_intl.chatapp.service

import android.app.Activity
import android.content.Intent

/**
 * Receiver implementation for broadcast messages sent when an in-app message is sent.
 * @param activity The activity where the receiver is registered.
 * @see Receiver
 */
class MessageReceiver(activity: Activity) : Receiver(activity) {
    override val actionName: String
        get() = "MESSAGE_SENT"

    override val intent: Intent by lazy { Intent() }
}
