package com.abhishek.githubusers.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abhishek.githubusers.data.model.UsersItemDto
import com.abhishek.githubusers.ui.model.UsersItemUi

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val login: String,
    val avatarUrl: String,
)
