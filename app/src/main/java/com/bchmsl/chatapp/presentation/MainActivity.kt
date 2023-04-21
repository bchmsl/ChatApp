package com.bchmsl.chatapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bchmsl.chatapp.R
import com.bchmsl.chatapp.databinding.ActivityMainBinding
import com.bchmsl.chatapp.presentation.model.UserTags
import com.bchmsl.chatapp.presentation.ui.chat.ChatFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFragments()
    }

    private fun setupFragments() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fcvFirst, ChatFragment(), UserTags.FIRST_USER_TAG.name)
            add(R.id.fcvSecond, ChatFragment(), UserTags.SECOND_USER_TAG.name)
        }.commit()
    }
}