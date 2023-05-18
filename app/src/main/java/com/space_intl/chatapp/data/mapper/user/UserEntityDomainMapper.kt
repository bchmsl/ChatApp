package com.space_intl.chatapp.data.mapper.user

import com.space_intl.chatapp.common.mapper.ModelMapper
import com.space_intl.chatapp.data.local.model.UserEntity
import com.space_intl.chatapp.domain.model.UserDomainModel

/**
 * Mapper class used to transform [UserEntity] (in the data layer) to [UserDomainModel] in the
 * domain layer.
 * @see ModelMapper
 * @see UserEntity
 * @see UserDomainModel
 */
class UserEntityDomainMapper : ModelMapper<UserEntity, UserDomainModel> {
    override fun invoke(model: UserEntity): UserDomainModel =
        UserDomainModel(userName = model.userName)
}
