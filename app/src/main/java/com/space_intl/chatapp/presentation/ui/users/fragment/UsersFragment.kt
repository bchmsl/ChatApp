package com.space_intl.chatapp.presentation.ui.users.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.space_intl.chatapp.R
import com.space_intl.chatapp.common.extensions.collectAsync
import com.space_intl.chatapp.common.extensions.isOrientationLandscape
import com.space_intl.chatapp.databinding.FragmentUsersBinding
import com.space_intl.chatapp.presentation.activity.ChatActivity
import com.space_intl.chatapp.presentation.base.adapter.OnClickListener
import com.space_intl.chatapp.presentation.base.fragment.BaseFragment
import com.space_intl.chatapp.presentation.base.fragment.Inflater
import com.space_intl.chatapp.presentation.ui.chat.fragment.ChatFragment
import com.space_intl.chatapp.presentation.ui.users.adapter.UsersAdapter
import com.space_intl.chatapp.presentation.ui.users.model.UserUIModel
import com.space_intl.chatapp.presentation.ui.users.viewmodel.UsersViewModel
import kotlin.reflect.KClass


/**
 * Fragment for listing and creating users.
 * @see BaseFragment
 * @see UsersViewModel
 * @see UsersAdapter
 * @see ChatFragment
 */
class UsersFragment : BaseFragment<FragmentUsersBinding, UsersViewModel>() {

    private val usersAdapter by lazy { UsersAdapter() }

    override val viewModelClass: KClass<UsersViewModel>
        get() = UsersViewModel::class

    override fun inflate(): Inflater<FragmentUsersBinding> = FragmentUsersBinding::inflate

    override fun onBind() {
        loadContent()
        listeners()
    }

    private fun listeners() {
        binding.addUserButton.setOnClickListener {
            openChat(usersAdapter.itemCount, true)
        }
        usersAdapter.setListener(object : OnClickListener<UserUIModel> {
            override fun onClick(item: UserUIModel, position: Int) {
                // Opens the chat fragment
                openChat(position, false)
                var list = usersAdapter.currentList.toMutableList()
                list = list.map {
                    UserUIModel(it.userName, false)
                }.toMutableList()
                list[position] = list[position].copy(isOpened = true)
                usersAdapter.submitList(list.toList())
            }
        })
        // Switches day and night mode
        binding.switchToggleButton.setOnClickListener {
            val switch = binding.switchToggleButton
            AppCompatDelegate.setDefaultNightMode(if (switch.isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun openChat(index: Int, isNewChat: Boolean) {
        // Create new chat instance
        val fragment by lazy { ChatFragment() }
        val userName = "User ${index.inc()}"
        if (isNewChat) vm.saveUser(userName)
        val bundle by lazy { Bundle() }

        // Add username argument into Chat bundle
        bundle.putString(ChatActivity.EXTRA_TAG, userName)
        fragment.arguments = bundle

        // Open Chat fragment
        val transaction =
            parentFragmentManager.beginTransaction().replace(R.id.mainContainer, fragment)

        // This check does not work, I don't know why
        if (!(activity as AppCompatActivity).isOrientationLandscape()) {
            transaction.addToBackStack("transaction")
        }

        transaction.commit()
    }

    private fun loadContent() {
        // Loads the content after binding
        vm.retrieveUsers()
        binding.usersRecyclerView.adapter = usersAdapter
        collectAsync(vm.usersState) { users ->
            usersAdapter.submitList(users.toList())
        }
    }


}
