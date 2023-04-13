package com.bchmsl.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bchmsl.chatapp.databinding.ActivityMainBinding
import com.bchmsl.chatapp.presentation.first_user.FirstUserFragment
import com.bchmsl.chatapp.presentation.second_user.SecondUserFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fcvFirst, FirstUserFragment(), null)
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fcvSecond, SecondUserFragment(), null)
            .commit()

    }
}