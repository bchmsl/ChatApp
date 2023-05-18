package com.space_intl.chatapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*
import com.space_intl.chatapp.common.extensions.isOrientationLandscape
import com.space_intl.chatapp.common.extensions.setFragmentToContainer
import com.space_intl.chatapp.databinding.ActivityChatBinding
import com.space_intl.chatapp.presentation.ui.users.fragment.UsersFragment

/**
 * Activity for the application.
 * @see AppCompatActivity
 */
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the default night mode to dark mode.
        if (getDefaultNightMode() == MODE_NIGHT_FOLLOW_SYSTEM || getDefaultNightMode() == MODE_NIGHT_UNSPECIFIED) {
            setDefaultNightMode(MODE_NIGHT_YES)
        }

        // If the activity is created for the first time, setup the fragments.
        if (savedInstanceState == null) {
            setupFragments()
        }
    }

    /**
     * Sets up the fragments to the FragmentContainers.
     * @see setFragmentToContainer
     */
    private fun setupFragments() {
        with(binding) {
            if (isOrientationLandscape()) {
                if (landscapeUsersFragment != null) {
                    setFragmentToContainer(landscapeUsersFragment, UsersFragment())
                } else return
            } else {
                setFragmentToContainer(mainContainer, UsersFragment())
            }
        }
    }

    companion object {
        const val EXTRA_TAG = "userId"
    }
}
