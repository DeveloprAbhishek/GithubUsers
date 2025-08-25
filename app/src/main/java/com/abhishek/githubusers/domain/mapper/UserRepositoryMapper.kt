package com.abhishek.githubusers.domain.mapper

import com.abhishek.githubusers.data.db.UserRepositoryEntity
import com.abhishek.githubusers.data.model.UserRepositoryDto

interface UserRepositoryMapper {
    fun userRepositoryToEntityList(
        dtoList: List<UserRepositoryDto>,
        userLogin: String
    ): List<UserRepositoryEntity>
}
