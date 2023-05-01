package com.bchmsl.chatapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.appcompat.widget.AppCompatToggleButton
import com.bchmsl.chatapp.databinding.ActivityChatBinding
import com.bchmsl.chatapp.presentation.model.UserTags
import com.bchmsl.chatapp.presentation.ui.chat.ChatFragment

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
            tbtnSwitch.setOnClickListener {
                checkSwitchState(tbtnSwitch)
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
                add(binding.fcvFirst.id, ChatFragment(), UserTags.FirstUser.name)
                add(binding.fcvSecond.id, ChatFragment(), UserTags.SecondUser.name)
            } else {
                replace(binding.fcvFirst.id, ChatFragment(), UserTags.FirstUser.name)
                replace(binding.fcvSecond.id, ChatFragment(), UserTags.SecondUser.name)
            }
        }.commit()
    }
}