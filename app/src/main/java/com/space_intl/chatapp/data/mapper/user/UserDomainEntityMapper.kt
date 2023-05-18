package com.space_intl.chatapp.data.mapper.user

import com.space_intl.chatapp.common.mapper.ModelMapper
import com.space_intl.chatapp.data.local.model.UserEntity
import com.space_intl.chatapp.domain.model.UserDomainModel

class UserDomainEntityMapper : ModelMapper<UserDomainModel, UserEntity> {

    override fun invoke(model: UserDomainModel): UserEntity =
        UserEntity(0, model.userName)
}
