package com.bchmsl.chatapp.common.extensions

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch


fun Fragment.log(message: String){
    Log.d(this.tag, message)
}

fun Fragment.async(lifecycleState: Lifecycle.State = Lifecycle.State.RESUMED, block: suspend ()-> Unit){
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(lifecycleState){
            block.invoke()
        }
    }
}