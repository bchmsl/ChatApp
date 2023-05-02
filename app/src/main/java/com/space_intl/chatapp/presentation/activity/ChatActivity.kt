package com.space_intl.chatapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.appcompat.widget.AppCompatToggleButton
import com.space_intl.chatapp.common.extensions.setFragmentToContainer
import com.space_intl.chatapp.databinding.ActivityChatBinding
import com.space_intl.chatapp.presentation.ui.chat.fragment.ChatFragment

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (getDefaultNightMode() == MODE_NIGHT_FOLLOW_SYSTEM ||
            getDefaultNightMode() == MODE_NIGHT_UNSPECIFIED
        ) {
            setDefaultNightMode(MODE_NIGHT_YES)
        }
        setupFragments()
        switchListener()
    }


    private fun switchListener() {
        with(binding) {
            switchToggleButton.setOnClickListener {
                checkSwitchState(switchToggleButton)
            }
        }
    }

    private fun checkSwitchState(switch: AppCompatToggleButton) {
        setDefaultNightMode(if (switch.isChecked) MODE_NIGHT_YES else MODE_NIGHT_NO)

    }

    private fun setupFragments() {
        with(binding) {
            setFragmentToContainer(firstFragmentContainer, ChatFragment())
            setFragmentToContainer(secondFragmentContainer, ChatFragment())
        }
    }
}

