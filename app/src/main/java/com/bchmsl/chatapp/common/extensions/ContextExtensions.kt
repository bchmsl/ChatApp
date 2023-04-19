package com.bchmsl.chatapp.common.extensions

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

fun Context.getCurrentDateTime(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return dateFormat.format(Date())
}