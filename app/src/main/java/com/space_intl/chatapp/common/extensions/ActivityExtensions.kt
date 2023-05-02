package com.space_intl.chatapp.common.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView

fun AppCompatActivity.setFragmentToContainer(container: FragmentContainerView, fragment: Fragment) {
    supportFragmentManager.beginTransaction().apply {
        if (savedStateRegistry.isRestored) {
            replace(container.id, fragment, container.id.toString())
        }else{
            add(container.id, fragment, container.id.toString())
        }
    }.commit()
}