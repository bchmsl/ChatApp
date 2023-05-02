package com.space_intl.chatapp.common.extensions

import android.content.res.ColorStateList
import android.widget.TextView
import androidx.annotation.ColorRes

fun TextView.setBackgroundTint(@ColorRes color: Int) {
    this.backgroundTintList = ColorStateList.valueOf(this.context.getColor(color))
}
