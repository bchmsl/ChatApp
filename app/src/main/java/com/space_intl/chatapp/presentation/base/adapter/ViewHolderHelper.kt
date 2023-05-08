package com.space_intl.chatapp.presentation.base.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import com.space_intl.chatapp.common.extensions.setBackgroundTint
import com.space_intl.chatapp.common.extensions.setTint

interface ViewHolderHelper {
    fun setTextColor(@ColorRes color: Int, vararg textViews: TextView) {
        textViews.forEach { t ->
            t.setTextColor(t.context.getColor(color))
        }
    }

    fun setScaleX(scale: Float, vararg views: View) {
        views.forEach { v ->
            v.scaleX = scale
        }
    }

    fun setAlpha(alpha: Float, vararg views: View) {
        views.forEach { v ->
            v.alpha = alpha
        }
    }

    fun setColor(@ColorRes color: Int, vararg views: View) {
        views.forEach { v ->
            when (v) {
                is TextView -> v.setBackgroundTint(color)
                is ImageView -> v.setTint(color)
            }
        }
    }
}