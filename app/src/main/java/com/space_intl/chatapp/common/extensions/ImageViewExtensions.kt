package com.space_intl.chatapp.common.extensions

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.ColorRes

fun ImageView.setTint(@ColorRes color:Int){
    this.imageTintList = ColorStateList.valueOf(this.context.getColor(color))
}