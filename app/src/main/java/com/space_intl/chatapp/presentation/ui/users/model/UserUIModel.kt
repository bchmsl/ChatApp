package com.space_intl.chatapp.presentation.ui.users.model

/**
 * Representation for a [UserUIModel] fetched from an external layer data source
 */
data class UserUIModel(
    val userName: String,
    var isOpened: Boolean = false
)
