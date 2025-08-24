package com.abhishek.githubusers.ui.model

data class UserRepositoryUi(
    val description: String?,
    val fork: Boolean,
    val forksCount: Int,
    val fullName: String,
    val language: String,
    val name: String,
    val private: Boolean,
    val stargazersCount: Int
)
