package com.space_intl.chatapp.common.extensions

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView


/**
 * Extension function to add fragment to container if it is not restored.
 * Replace fragment if it is restored.
 * @param container FragmentContainerView
 * @param fragment Fragment
 */

fun AppCompatActivity.setFragmentToContainer(
    container: FragmentContainerView,
    fragment: Fragment
) {
    supportFragmentManager.beginTransaction().apply {
        if (savedStateRegistry.isRestored) {
            replace(container.id, fragment)
        } else {
            add(container.id, fragment)
        }
    }.commit()
}

fun AppCompatActivity.isOrientationLandscape(): Boolean {
    return when (requestedOrientation) {
        (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE or ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE or ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE or ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE) -> true
        else -> false
    }
}
