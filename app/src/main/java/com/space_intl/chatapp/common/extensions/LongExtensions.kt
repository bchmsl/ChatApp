package com.space_intl.chatapp.common.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Extension function to convert Millisecond date to formatted date.
 */

fun Long.toFormattedDate(): String {
    val date = Date(this)
    val calendar = Calendar.getInstance()
    val locale = Locale("ka", "GE")
    calendar.time = date

    val today = Calendar.getInstance()

    val yesterday = Calendar.getInstance()
    yesterday.add(Calendar.DATE, -1)

    val lastWeek = Calendar.getInstance()
    lastWeek.add(Calendar.DATE, -7)

    return when {
        calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) &&
                calendar.get(Calendar.HOUR_OF_DAY) == today.get(Calendar.HOUR_OF_DAY) &&
                calendar.get(Calendar.MINUTE) >= today.get(Calendar.MINUTE) - 3 -> {
            "ახლახანს"
        }
        calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) -> {
            SimpleDateFormat("HH:mm", locale).format(date)
        }
        calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) &&
                calendar.get(Calendar.DAY_OF_YEAR) == yesterday.get(Calendar.DAY_OF_YEAR) -> {
            "გუშინ"
        }
        calendar.get(Calendar.YEAR) == lastWeek.get(Calendar.YEAR) &&
                calendar.get(Calendar.WEEK_OF_YEAR) == lastWeek.get(Calendar.WEEK_OF_YEAR) -> {
            SimpleDateFormat("EEEE", locale).format(date)
        }
        else -> {
            SimpleDateFormat("dd MMM yyyy", locale).format(date)
        }
    }.uppercase()
}
