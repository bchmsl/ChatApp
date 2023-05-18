package com.space_intl.chatapp.presentation.ui.users.viewmodel

import androidx.lifecycle.ViewModel
import com.space_intl.chatapp.common.extensions.executeAsync
import com.space_intl.chatapp.domain.repository.UserRepository
import com.space_intl.chatapp.presentation.ui.users.model.UserUIModel
import com.space_intl.chatapp.presentation.ui.users.model.mapper.UserDomainUIMapper
import com.space_intl.chatapp.presentation.ui.users.model.mapper.UserUIDomainMapper
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UsersViewModel(
    private val userRepository: UserRepository,
    private val userDomainUIMapper: UserDomainUIMapper,
    private val userUIDomainMapper: UserUIDomainMapper
) : ViewModel() {

    private val _usersState = MutableStateFlow<List<UserUIModel>>(emptyList())
    val usersState get() = _usersState.asStateFlow()

    fun retrieveUsers() {
        executeAsync(IO) {
            userRepository.retrieveUsers().collect { users ->
                _usersState.emit(users.map { userDomainUIMapper(it) })
            }
        }
    }

    fun saveUser(userName: String) {
        executeAsync {
            val user = UserUIModel(userName)
            userRepository.saveUser(userUIDomainMapper(user))
        }
    }
}
