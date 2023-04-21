package com.bchmsl.chatapp.common.providers

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDateTime(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return dateFormat.format(Date())
}