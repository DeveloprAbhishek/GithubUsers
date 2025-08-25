package com.abhishek.githubusers.data.mapper

import com.abhishek.githubusers.data.db.UserRepositoryEntity
import com.abhishek.githubusers.data.model.UserRepositoryDto
import com.abhishek.githubusers.domain.mapper.UserRepositoryMapper
import javax.inject.Inject

class UserRepositoryMapperImpl @Inject constructor() : UserRepositoryMapper {
    override fun userRepositoryToEntityList(
        dtoList: List<UserRepositoryDto>,
        userLogin: String
    ): List<UserRepositoryEntity> {
        return dtoList.map { dto ->
            UserRepositoryEntity(
                id = dto.id ?: 0,
                userLogin = userLogin,
                description = dto.description,
                fork = dto.fork,
                forksCount = dto.forksCount,
                fullName = dto.fullName,
                language = dto.language,
                name = dto.name,
                isPrivate = dto.private,
                stargazersCount = dto.stargazersCount
            )
        }
    }
}
