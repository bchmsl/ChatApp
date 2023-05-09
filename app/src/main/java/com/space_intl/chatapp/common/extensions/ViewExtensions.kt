package com.space_intl.chatapp.common.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.space_intl.chatapp.R

/**
 * Extension function to show snackbar.
 * @param message Message to show in snackbar.
 * @param isError Boolean to show error snackbar.
 * @return [Snackbar] object to be used for further operations.
 */

fun View.makeSnackbar(message: String, isError: Boolean): Snackbar {
    val color =
        if (isError) resources.getColor(R.color.error_label, null)
        else resources.getColor(R.color.neutral_05_lightest_grey, null)
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG).setBackgroundTint(color)
    snackbar.show()
    return snackbar
}
