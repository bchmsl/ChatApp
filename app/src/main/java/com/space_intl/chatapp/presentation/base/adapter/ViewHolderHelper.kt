package com.space_intl.chatapp.presentation.base.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import com.space_intl.chatapp.common.extensions.setBackgroundTint
import com.space_intl.chatapp.common.extensions.setTint

/**
 * Interface for the view holder helper.
 */
interface ViewHolderHelper {

    /**
     * Sets the text color of the text views.
     * @param color the color resource.
     * @param textViews the text views.
     */
    fun setTextColor(@ColorRes color: Int, vararg textViews: TextView) {
        textViews.forEach { t ->
            t.setTextColor(t.context.getColor(color))
        }
    }

    /**
     * Sets the scale of the views.
     * @param scale the scale.
     * @param views the views.
     */
    fun setScaleX(scale: Float, vararg views: View) {
        views.forEach { v ->
            v.scaleX = scale
        }
    }

    /**
     * Sets the alpha of the views.
     * @param alpha the alpha.
     * @param views the views.
     */
    fun setAlpha(alpha: Float, vararg views: View) {
        views.forEach { v ->
            v.alpha = alpha
        }
    }

    /**
     * Sets the color of the views.
     * @param color the color.
     * @param views the views.
     */
    fun setColor(@ColorRes color: Int, vararg views: View) {
        views.forEach { v ->
            when (v) {
                is TextView -> v.setBackgroundTint(color)
                is ImageView -> v.setTint(color)
            }
        }
    }
}
