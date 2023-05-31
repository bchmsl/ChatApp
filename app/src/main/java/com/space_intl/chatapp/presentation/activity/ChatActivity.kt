package com.space_intl.chatapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.appcompat.widget.AppCompatToggleButton
import com.space_intl.chatapp.common.extensions.setFragmentToContainer
import com.space_intl.chatapp.databinding.ActivityChatBinding
import com.space_intl.chatapp.presentation.ui.chat.fragment.ChatFragment

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

        setSwitchListener()
    }

    private fun setSwitchListener() {
        with(binding) {
            switchToggleButton.setOnClickListener {
                checkSwitchState(switchToggleButton)
            }
        }
    }

    /**
     * Checks the state of the switch and sets the night mode accordingly.
     * @param switch the switch.
     */
    private fun checkSwitchState(switch: AppCompatToggleButton) {
        setDefaultNightMode(if (switch.isChecked) MODE_NIGHT_YES else MODE_NIGHT_NO)

    }

    /**
     * Sets up the fragments to the FragmentContainers.
     * @see setFragmentToContainer
     */
    private fun setupFragments() {
        with(binding) {
            val containers = listOf(firstFragmentContainer, secondFragmentContainer)
            containers.forEachIndexed { index, container ->
                val fragment = ChatFragment()
                val bundle = Bundle()
                bundle.putString(EXTRA_TAG, "fragment_${index.inc()}")
                fragment.arguments = bundle
                setFragmentToContainer(
                    container, fragment
                )
            }
        }
    }

    companion object {
        const val EXTRA_TAG = "userId"
    }
}
