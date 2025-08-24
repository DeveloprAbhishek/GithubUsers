package com.abhishek.githubusers.ui.mapper

import com.abhishek.githubusers.data.db.UserEntity
import com.abhishek.githubusers.domain.mapper.UserUiMapper
import com.abhishek.githubusers.ui.model.UsersItemUi
import javax.inject.Inject

class UserUiMapperImpl @Inject constructor() : UserUiMapper {
    override fun entityToUi(entity: UserEntity): UsersItemUi =
        UsersItemUi(
            id = entity.id,
            login = entity.login,
            avatarUrl = entity.avatarUrl
        )
}
