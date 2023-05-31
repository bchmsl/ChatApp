package com.space_intl.chatapp.data.mapper.user

import com.space_intl.chatapp.common.mapper.ModelMapper
import com.space_intl.chatapp.data.local.model.UserEntity
import com.space_intl.chatapp.domain.model.UserDomainModel

/**
 * Mapper class used to transform [UserDomainModel] (in the domain layer) to [UserEntity] in the
 * data layer.
 * @see ModelMapper
 * @see UserDomainModel
 * @see UserEntity
 */
class UserDomainEntityMapper : ModelMapper<UserDomainModel, UserEntity> {

    override fun invoke(model: UserDomainModel): UserEntity =
        UserEntity(0, model.userName)
}
