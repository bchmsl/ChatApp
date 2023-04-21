package com.bchmsl.chatapp.common.extensions

import android.content.res.ColorStateList
import android.widget.TextView

fun TextView.setBackgroundTint(color:Int){
    this.backgroundTintList = ColorStateList.valueOf(this.context.getColor(color))
}