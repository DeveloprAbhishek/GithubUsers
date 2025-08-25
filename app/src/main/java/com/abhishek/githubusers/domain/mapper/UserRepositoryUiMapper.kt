package com.abhishek.githubusers.domain.mapper

import com.abhishek.githubusers.data.db.UserRepositoryEntity
import com.abhishek.githubusers.ui.model.UserRepositoryUi

interface UserRepositoryUiMapper {
    fun entityToUi(entities: List<UserRepositoryEntity>): List<UserRepositoryUi>
}