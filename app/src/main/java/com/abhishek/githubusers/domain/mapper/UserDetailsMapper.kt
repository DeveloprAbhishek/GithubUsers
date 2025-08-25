package com.abhishek.githubusers.domain.mapper

import com.abhishek.githubusers.data.db.UserDetailsEntity
import com.abhishek.githubusers.data.model.UserDetailsDto

interface UserDetailsMapper {
    fun userDetailsDtoToEntity(dto: UserDetailsDto): UserDetailsEntity
}
