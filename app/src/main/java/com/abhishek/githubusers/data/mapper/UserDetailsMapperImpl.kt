package com.abhishek.githubusers.data.mapper

import com.abhishek.githubusers.data.db.UserDetailsEntity
import com.abhishek.githubusers.data.model.UserDetailsDto
import com.abhishek.githubusers.domain.mapper.UserDetailsMapper
import javax.inject.Inject

class UserDetailsMapperImpl @Inject constructor() : UserDetailsMapper {
    override fun userDetailsDtoToEntity(dto: UserDetailsDto): UserDetailsEntity {
        return UserDetailsEntity(
            login = dto.login.orEmpty(),
            avatarUrl = dto.avatarUrl,
            bio = dto.bio,
            blog = dto.blog,
            company = dto.company,
            email = dto.email,
            followers = dto.followers,
            following = dto.following,
            location = dto.location,
            name = dto.name,
            publicRepos = dto.publicRepos
        )
    }
}
