package com.abhishek.githubusers.data.repository

import com.abhishek.githubusers.data.db.UserDao
import com.abhishek.githubusers.data.db.UserEntity
import com.abhishek.githubusers.data.network.ApiService
import com.abhishek.githubusers.domain.mapper.UserDataMapper
import com.abhishek.githubusers.domain.mapper.UserDetailsMapper
import com.abhishek.githubusers.domain.mapper.UserRepositoryMapper
import com.abhishek.githubusers.ui.model.UserDetailsUi
import com.abhishek.githubusers.ui.model.UserRepositoryUi
import com.abhishek.githubusers.utils.Result
import com.abhishek.githubusers.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val mapper: UserDataMapper,
    private val userDetailsMapper: UserDetailsMapper,
    private val userRepositoryMapper: UserRepositoryMapper
) {
    fun refreshUsers(): Flow<Result<Unit>> = safeApiCall {
        val usersFromApi = apiService.getUsers()
        userDao.clearAllUsers()
        userDao.insertUsers(usersFromApi.map { mapper.dtoToEntity(it) })
    }

    fun getAllUsers(): Flow<List<UserEntity>> = userDao.getAllUsers()

    fun searchUsers(query: String): Flow<List<UserEntity>> = userDao.searchUsers("%$query%")

    fun getUserDetails(username: String): Flow<Result<UserDetailsUi>> {
        return safeApiCall {
            apiService.getUserDetails(username)
        }.map { result ->
            when (result) {
                is Result.Success -> Result.Success(userDetailsMapper.toUserDetailsUi(result.data))
                is Result.Error -> result
                is Result.Loading -> Result.Loading
            }
        }
    }

    fun getUserRepositories(username: String): Flow<Result<List<UserRepositoryUi>>> {
        return safeApiCall {
            apiService.getUserRepositories(username)
        }.map { result ->
            when (result) {
                is Result.Success -> {
                    val uiRepos = result.data.map { userRepositoryMapper.toUserRepositoryUi(it) }
                    Result.Success(uiRepos)
                }

                is Result.Error -> result
                is Result.Loading -> Result.Loading
            }
        }
    }
}
