package com.space_intl.chatapp.common.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView


/**
 * Extension function to add fragment to container if it is not restored.
 * Replace fragment if it is restored.
 * @param container FragmentContainerView
 * @param fragment Fragment
 * @param tag String
 */

fun AppCompatActivity.setFragmentToContainer(
    container: FragmentContainerView,
    fragment: Fragment,
    tag: String
) {
    supportFragmentManager.beginTransaction().apply {
        if (savedStateRegistry.isRestored) {
            replace(container.id, fragment, tag)
        } else {
            add(container.id, fragment, tag)
        }
    }.commit()
}
