package com.abhishek.githubusers.utils

object AppConstants {
    const val BASE_URL = "https://api.github.com/"
    const val GET_USERS = "users"
    const val GET_USER_DETAILS = "users/{username}"
    const val GET_USER_REPOS = "users/{username}/repos"
    const val USERNAME_PATH = "username"

    const val UNKNOWN_ERROR = "An unknown error occurred"
    const val NETWORK_ERROR = "Couldn't reach server. Check your internet connection."
    const val DEBOUNCE_TIMEOUT = 500L
    const val DB_NAME = "github_users_db"
    const val GITHUB_USERS = "Github Users"
    const val USERS_DETAILS = "User Details"
    const val CLEAR_SEARCH_QUERY = "Clear search query"
    const val SEARCH_USERS = "Search Users"
}
