package com.abhishek.githubusers.ui.model

data class UserDetailsUi(
    val avatarUrl: String,
    val bio: String?,
    val blog: String?,
    val company: String?,
    val followers: Int,
    val following: Int,
    val location: String?,
    val login: String,
    val name: String,
    val publicRepos: Int,
    val email: String?,
)
