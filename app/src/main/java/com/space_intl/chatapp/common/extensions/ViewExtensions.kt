package com.space_intl.chatapp.common.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.space_intl.chatapp.R

fun View.makeSnackbar(message:String, isError: Boolean){
    val color = if (isError) resources.getColor(R.color.error_label, null) else resources.getColor(R.color.neutral_05_lightest_grey, null)
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).setBackgroundTint(color).show()
}