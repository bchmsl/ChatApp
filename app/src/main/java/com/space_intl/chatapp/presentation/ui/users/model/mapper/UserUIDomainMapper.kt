package com.space_intl.chatapp.presentation.ui.users.model.mapper

import com.space_intl.chatapp.common.mapper.ModelMapper
import com.space_intl.chatapp.domain.model.UserDomainModel
import com.space_intl.chatapp.presentation.ui.users.model.UserUIModel

/**
 * Mapper class used to transform [UserUIModel] (in the presentation layer) to
 * [UserDomainModel] in the domain layer.
 * @see ModelMapper
 * @see UserUIModel
 * @see UserDomainModel
 */
class UserUIDomainMapper : ModelMapper<UserUIModel, UserDomainModel> {
    override fun invoke(model: UserUIModel): UserDomainModel =
        UserDomainModel(userName = model.userName)
}
