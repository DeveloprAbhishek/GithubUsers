package com.abhishek.githubusers.data.repository

import com.abhishek.githubusers.data.model.Users
import com.abhishek.githubusers.data.network.ApiService
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getUsersData(): Users {
        return apiService.getUsers()
    }
}
