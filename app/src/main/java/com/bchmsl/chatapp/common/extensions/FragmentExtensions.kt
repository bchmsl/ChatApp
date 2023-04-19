package com.bchmsl.chatapp.common.extensions

import android.util.Log
import androidx.fragment.app.Fragment


fun Fragment.log(message: String){
    Log.d(this.tag, message)
}