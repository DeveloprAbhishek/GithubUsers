package com.abhishek.githubusers.data.db

import androidx.room.Embedded
import androidx.room.Relation

data class UserDetailsWithRepos(
    @Embedded
    val userDetails: UserDetailsEntity,
    @Relation(
        parentColumn = "login",
        entityColumn = "userLogin"
    )
    val repositories: List<UserRepositoryEntity>
)
