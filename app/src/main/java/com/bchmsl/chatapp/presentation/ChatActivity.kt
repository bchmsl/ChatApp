package com.bchmsl.chatapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bchmsl.chatapp.databinding.ActivityChatBinding
import com.bchmsl.chatapp.presentation.model.UserTags
import com.bchmsl.chatapp.presentation.ui.chat.ChatFragment

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFragments(savedInstanceState)
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