package com.abhishek.githubusers.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_repositories",
    foreignKeys = [
        ForeignKey(
            entity = UserDetailsEntity::class,
            parentColumns = ["login"],
            childColumns = ["userLogin"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("userLogin"), Index("id")]
)
data class UserRepositoryEntity(
    @PrimaryKey
    val id: Int,
    val userLogin: String,
    val description: String?,
    val fork: Boolean?,
    val forksCount: Int?,
    val fullName: String?,
    val language: String?,
    val name: String?,
    val isPrivate: Boolean?,
    val stargazersCount: Int?
)
