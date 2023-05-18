package com.space_intl.chatapp.presentation.ui.users.model.mapper

import com.space_intl.chatapp.common.mapper.ModelMapper
import com.space_intl.chatapp.domain.model.UserDomainModel
import com.space_intl.chatapp.presentation.ui.users.model.UserUIModel

class UserUIDomainMapper : ModelMapper<UserUIModel, UserDomainModel> {
    override fun invoke(model: UserUIModel): UserDomainModel =
        UserDomainModel(userName = model.userName)
}
