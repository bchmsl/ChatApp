package com.space_intl.chatapp.presentation.ui.users.model.mapper

import com.space_intl.chatapp.common.mapper.ModelMapper
import com.space_intl.chatapp.domain.model.UserDomainModel
import com.space_intl.chatapp.presentation.ui.users.model.UserUIModel

/**
 * Mapper class used to transform [UserDomainModel] (in the domain layer) to [UserUIModel] in the
 * presentation layer.
 * @see ModelMapper
 * @see UserDomainModel
 * @see UserUIModel
 */
class UserDomainUIMapper : ModelMapper<UserDomainModel, UserUIModel> {
    override fun invoke(model: UserDomainModel): UserUIModel =
        UserUIModel(userName = model.userName)
}
