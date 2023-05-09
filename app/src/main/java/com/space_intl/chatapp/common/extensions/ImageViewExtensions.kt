package com.space_intl.chatapp.common.extensions

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.ColorRes

/**
 * Extension function to set tint color to the ImageView.
 * @param color Color resource to set tint.
 */

fun ImageView.setTint(@ColorRes color: Int) {
    this.imageTintList = ColorStateList.valueOf(this.context.getColor(color))
}
