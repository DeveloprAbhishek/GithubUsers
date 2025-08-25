package com.abhishek.githubusers.ui.mapper

import com.abhishek.githubusers.data.db.UserRepositoryEntity
import com.abhishek.githubusers.domain.mapper.UserRepositoryUiMapper
import com.abhishek.githubusers.ui.model.UserRepositoryUi
import javax.inject.Inject

class UserRepositoryUiMapperImpl @Inject constructor() : UserRepositoryUiMapper {
    override fun entityToUi(entities: List<UserRepositoryEntity>): List<UserRepositoryUi> {
        return entities.map { entity ->
            UserRepositoryUi(
                id = entity.id,
                description = entity.description,
                fork = entity.fork ?: false,
                forksCount = entity.forksCount ?: 0,
                fullName = entity.fullName.orEmpty(),
                language = entity.language.orEmpty(),
                name = entity.name.orEmpty(),
                isPrivate = entity.isPrivate ?: false,
                stargazersCount = entity.stargazersCount ?: 0
            )
        }
    }
}
