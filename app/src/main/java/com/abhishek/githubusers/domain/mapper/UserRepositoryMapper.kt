package com.abhishek.githubusers.domain.mapper

import com.abhishek.githubusers.data.model.UserRepositoryDto
import com.abhishek.githubusers.ui.model.UserRepositoryUi
import javax.inject.Inject

class UserRepositoryMapper @Inject constructor() {
    fun toUserRepositoryUi(dto: UserRepositoryDto): UserRepositoryUi {
        return UserRepositoryUi(
            description = dto.description,
            fork = dto.fork ?: false,
            forksCount = dto.forksCount ?: 0,
            fullName = dto.fullName.orEmpty(),
            language = dto.language.orEmpty(),
            name = dto.name.orEmpty(),
            private = dto.private ?: false,
            stargazersCount = dto.stargazersCount ?: 0
        )
    }
}
