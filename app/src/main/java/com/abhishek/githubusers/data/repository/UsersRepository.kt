package com.abhishek.githubusers.data.repository

import com.abhishek.githubusers.data.db.UserDao
import com.abhishek.githubusers.data.db.UserEntity
import com.abhishek.githubusers.data.network.ApiService
import com.abhishek.githubusers.domain.mapper.UserDataMapper
import com.abhishek.githubusers.utils.Result
import com.abhishek.githubusers.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val mapper: UserDataMapper
) {
    fun refreshUsers(): Flow<Result<Unit>> = safeApiCall {
        val usersFromApi = apiService.getUsers()
        userDao.clearAllUsers()
        userDao.insertUsers(usersFromApi.map { mapper.dtoToEntity(it) })
    }

    fun getAllUsers(): Flow<List<UserEntity>> = userDao.getAllUsers()

    fun searchUsers(query: String): Flow<List<UserEntity>> = userDao.searchUsers("%$query%")
}
