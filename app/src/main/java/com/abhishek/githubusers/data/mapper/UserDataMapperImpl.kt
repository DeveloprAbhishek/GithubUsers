package com.abhishek.githubusers.data.mapper

import com.abhishek.githubusers.data.db.UserEntity
import com.abhishek.githubusers.data.model.UsersItemDto
import com.abhishek.githubusers.domain.mapper.UserDataMapper
import javax.inject.Inject

class UserDataMapperImpl @Inject constructor() : UserDataMapper {
    override fun dtoToEntity(dto: UsersItemDto): UserEntity =
        UserEntity(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl
        )
}