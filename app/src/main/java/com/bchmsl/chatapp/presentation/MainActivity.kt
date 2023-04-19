package com.bchmsl.chatapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bchmsl.chatapp.R
import com.bchmsl.chatapp.databinding.ActivityMainBinding
import com.bchmsl.chatapp.presentation.user.UserFragment

class MainActivity : AppCompatActivity() {
    companion object {
        const val FIRST_USER_TAG = "firstUser"
        const val SECOND_USER_TAG = "secondUser"
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFragments()
    }

    private fun setupFragments() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fcvFirst, UserFragment(), FIRST_USER_TAG)
            add(R.id.fcvSecond, UserFragment(), SECOND_USER_TAG)
        }.commit()
    }
}