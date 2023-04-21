package com.bchmsl.chatapp.common.extensions

import android.content.res.ColorStateList
import android.widget.ImageView

fun ImageView.setTint(color:Int){
    this.imageTintList = ColorStateList.valueOf(this.context.getColor(color))
}