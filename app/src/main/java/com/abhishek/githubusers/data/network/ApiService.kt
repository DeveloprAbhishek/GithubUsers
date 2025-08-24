package com.abhishek.githubusers.data.network

import com.abhishek.githubusers.data.model.UsersItemDto
import com.abhishek.githubusers.utils.AppConstants.GET_USERS
import retrofit2.http.GET

interface ApiService {
    @GET(GET_USERS)
    suspend fun getUsers(): List<UsersItemDto>
}
