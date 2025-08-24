package com.abhishek.githubusers.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val login: String,
    val avatarUrl: String,
)
