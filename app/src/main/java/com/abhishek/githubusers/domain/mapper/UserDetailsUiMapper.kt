package com.abhishek.githubusers.domain.mapper

import com.abhishek.githubusers.data.db.UserDetailsWithRepos
import com.abhishek.githubusers.ui.model.UserDetailsUi

interface UserDetailsUiMapper {
    fun entityToUi(entity: UserDetailsWithRepos): UserDetailsUi
}