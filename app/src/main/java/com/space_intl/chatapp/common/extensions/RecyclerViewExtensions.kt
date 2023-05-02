package com.space_intl.chatapp.common.extensions

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.scrollToBottom() {
    adapter?.let {
        if (it.itemCount > 0) {
            smoothScrollToPosition(it.itemCount - 1)
        }
    }
}