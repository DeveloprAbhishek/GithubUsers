package com.abhishek.githubusers.data.repository

import com.abhishek.githubusers.data.db.UserDao
import com.abhishek.githubusers.data.db.UserEntity
import com.abhishek.githubusers.data.network.ApiService
import com.abhishek.githubusers.domain.mapper.UserDataMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val mapper: UserDataMapper
) {
    suspend fun refreshUsers() {
        try {
            val usersFromApi = apiService.getUsers()
            userDao.clearAllUsers()
            userDao.insertUsers(usersFromApi.map { mapper.dtoToEntity(it) })
        } catch (e: Exception) {
            // In a real app, you'd want to handle this error more gracefully
            e.printStackTrace()
        }
    }

    fun getAllUsers(): Flow<List<UserEntity>> = userDao.getAllUsers()

    fun searchUsers(query: String): Flow<List<UserEntity>> = userDao.searchUsers("%$query%")
}
