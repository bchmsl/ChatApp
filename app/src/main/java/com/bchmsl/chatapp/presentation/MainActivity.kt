package com.bchmsl.chatapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bchmsl.chatapp.databinding.ActivityMainBinding
import com.bchmsl.chatapp.presentation.model.UserTags
import com.bchmsl.chatapp.presentation.ui.chat.ChatFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFragments(savedInstanceState)
    }

    private fun setupFragments(savedInstanceState: Bundle?) {
        supportFragmentManager.beginTransaction().apply {
            if (savedInstanceState == null) {
                add(binding.fcvFirst.id, ChatFragment(), UserTags.FIRST_USER_TAG.name)
                add(binding.fcvSecond.id, ChatFragment(), UserTags.SECOND_USER_TAG.name)
            } else {
                replace(binding.fcvFirst.id, ChatFragment(), UserTags.FIRST_USER_TAG.name)
                replace(binding.fcvSecond.id, ChatFragment(), UserTags.SECOND_USER_TAG.name)
            }
        }.commit()
    }
}