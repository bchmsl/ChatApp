package com.space_intl.chatapp.common.extensions

import android.content.res.ColorStateList
import android.widget.TextView
import androidx.annotation.ColorRes

/**
 * Extension function to set background tint color to the TextView.
 * @param color Color resource to set tint.
 */

fun TextView.setBackgroundTint(@ColorRes color: Int) {
    this.backgroundTintList = ColorStateList.valueOf(this.context.getColor(color))
}
