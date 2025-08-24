package com.abhishek.githubusers.data.network

import com.abhishek.githubusers.data.model.UserDetailsDto
import com.abhishek.githubusers.data.model.UserRepositoryDto
import com.abhishek.githubusers.data.model.UsersItemDto
import com.abhishek.githubusers.utils.AppConstants.GET_USERS
import com.abhishek.githubusers.utils.AppConstants.GET_USER_DETAILS
import com.abhishek.githubusers.utils.AppConstants.GET_USER_REPOS
import com.abhishek.githubusers.utils.AppConstants.USERNAME_PATH
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET(GET_USERS)
    suspend fun getUsers(): List<UsersItemDto>

    @GET(GET_USER_DETAILS)
    suspend fun getUserDetails(@Path(USERNAME_PATH) username: String): UserDetailsDto

    @GET(GET_USER_REPOS)
    suspend fun getUserRepositories(@Path(USERNAME_PATH) username: String): List<UserRepositoryDto>
}
