package com.abhishek.githubusers.ui.mapper

import com.abhishek.githubusers.data.db.UserDetailsWithRepos
import com.abhishek.githubusers.domain.mapper.UserDetailsUiMapper
import com.abhishek.githubusers.ui.model.UserDetailsUi
import javax.inject.Inject

class UserDetailsUiMapperImpl @Inject constructor() : UserDetailsUiMapper {
    override fun entityToUi(entity: UserDetailsWithRepos): UserDetailsUi {
        return UserDetailsUi(
            login = entity.userDetails.login,
            avatarUrl = entity.userDetails.avatarUrl.orEmpty(),
            bio = entity.userDetails.bio,
            blog = entity.userDetails.blog,
            company = entity.userDetails.company,
            email = entity.userDetails.email,
            followers = entity.userDetails.followers ?: 0,
            following = entity.userDetails.following ?: 0,
            location = entity.userDetails.location,
            name = entity.userDetails.name.orEmpty(),
            publicRepos = entity.userDetails.publicRepos ?: 0
        )
    }
}
