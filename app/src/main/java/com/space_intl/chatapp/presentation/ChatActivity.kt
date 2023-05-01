package com.space_intl.chatapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.appcompat.widget.AppCompatToggleButton
import com.space_intl.chatapp.databinding.ActivityChatBinding
import com.space_intl.chatapp.presentation.model.UserTags
import com.space_intl.chatapp.presentation.ui.chat.ChatFragment

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
        setupFragments(savedInstanceState)
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
        when (switch.isChecked) {
            true -> {
                setDefaultNightMode(MODE_NIGHT_YES)
            }
            false -> {
                setDefaultNightMode(MODE_NIGHT_NO)
            }
        }
    }

    private fun setupFragments(savedInstanceState: Bundle?) {
        supportFragmentManager.beginTransaction().apply {
            if (savedInstanceState == null) {
                add(binding.firstFragmentContainer.id, ChatFragment(), UserTags.FirstUser.name)
                add(binding.secondFragmentContainer.id, ChatFragment(), UserTags.SecondUser.name)
            } else {
                replace(binding.firstFragmentContainer.id, ChatFragment(), UserTags.FirstUser.name)
                replace(binding.secondFragmentContainer.id, ChatFragment(), UserTags.SecondUser.name)
            }
        }.commit()
    }
}