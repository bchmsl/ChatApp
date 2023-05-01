package com.space_intl.chatapp.common.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.toFormattedDate(): String {
    val date = Date(this)
    val calendar = Calendar.getInstance()
    calendar.time = date

    val today = Calendar.getInstance()

    val yesterday = Calendar.getInstance()
    yesterday.add(Calendar.DATE, -1)

    val lastWeek = Calendar.getInstance()
    lastWeek.add(Calendar.DATE, -7)

    return when {
        calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) -> {
            SimpleDateFormat("HH:mm", Locale.getDefault()).format(date)
        }
        calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) &&
                calendar.get(Calendar.DAY_OF_YEAR) == yesterday.get(Calendar.DAY_OF_YEAR) -> {
            "Yesterday"
        }
        calendar.get(Calendar.YEAR) == lastWeek.get(Calendar.YEAR) &&
                calendar.get(Calendar.WEEK_OF_YEAR) == lastWeek.get(Calendar.WEEK_OF_YEAR) -> {
            SimpleDateFormat("EEEE", Locale.getDefault()).format(date)
        }
        else -> {
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
        }
    }
}