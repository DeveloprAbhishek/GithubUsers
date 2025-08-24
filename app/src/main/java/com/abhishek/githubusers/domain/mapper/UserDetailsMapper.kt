package com.abhishek.githubusers.domain.mapper

import com.abhishek.githubusers.data.model.UserDetailsDto
import com.abhishek.githubusers.ui.model.UserDetailsUi
import javax.inject.Inject

class UserDetailsMapper @Inject constructor() {
    fun toUserDetailsUi(dto: UserDetailsDto): UserDetailsUi {
        return UserDetailsUi(
            avatarUrl = dto.avatarUrl.orEmpty(),
            bio = dto.bio,
            blog = dto.blog,
            company = dto.company,
            followers = dto.followers ?: 0,
            following = dto.following ?: 0,
            location = dto.location,
            login = dto.login.orEmpty(),
            name = dto.name.orEmpty(),
            publicRepos = dto.publicRepos ?: 0,
            email = dto.email
        )
    }
}
