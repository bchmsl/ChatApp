package com.space_intl.chatapp.presentation.ui.users.model.mapper

import com.space_intl.chatapp.common.mapper.ModelMapper
import com.space_intl.chatapp.domain.model.UserDomainModel
import com.space_intl.chatapp.presentation.ui.users.model.UserUIModel

class UserDomainUIMapper : ModelMapper<UserDomainModel, UserUIModel> {
    override fun invoke(model: UserDomainModel): UserUIModel =
        UserUIModel(userName = model.userName)
}
