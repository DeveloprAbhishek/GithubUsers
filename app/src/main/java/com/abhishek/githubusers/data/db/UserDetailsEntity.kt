package com.abhishek.githubusers.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_details")
data class UserDetailsEntity(
    @PrimaryKey
    val login: String,
    val avatarUrl: String?,
    val bio: String?,
    val blog: String?,
    val company: String?,
    val email: String?,
    val followers: Int?,
    val following: Int?,
    val location: String?,
    val name: String?,
    val publicRepos: Int?
)
