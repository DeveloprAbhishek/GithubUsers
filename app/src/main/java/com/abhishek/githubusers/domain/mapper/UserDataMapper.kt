package com.abhishek.githubusers.domain.mapper

import com.abhishek.githubusers.data.db.UserEntity
import com.abhishek.githubusers.data.model.UsersItemDto

interface UserDataMapper {
    fun dtoToEntity(dto: UsersItemDto): UserEntity
}
