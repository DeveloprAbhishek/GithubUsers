package com.abhishek.githubusers.domain.mapper

import com.abhishek.githubusers.data.db.UserEntity
import com.abhishek.githubusers.ui.model.UsersItemUi

interface UserUiMapper {
    fun entityToUi(entity: UserEntity): UsersItemUi
}
