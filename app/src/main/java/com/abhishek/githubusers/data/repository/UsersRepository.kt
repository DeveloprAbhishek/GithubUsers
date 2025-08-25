package com.abhishek.githubusers.data.repository

import com.abhishek.githubusers.data.db.UserDao
import com.abhishek.githubusers.data.db.UserEntity
import com.abhishek.githubusers.data.network.ApiService
import com.abhishek.githubusers.domain.mapper.UserDataMapper
import com.abhishek.githubusers.domain.mapper.UserDetailsMapper
import com.abhishek.githubusers.domain.mapper.UserDetailsUiMapper
import com.abhishek.githubusers.domain.mapper.UserRepositoryMapper
import com.abhishek.githubusers.domain.mapper.UserRepositoryUiMapper
import com.abhishek.githubusers.ui.model.UserDetailsUi
import com.abhishek.githubusers.ui.model.UserRepositoryUi
import com.abhishek.githubusers.utils.Result
import com.abhishek.githubusers.utils.safeApiCall
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val userDataMapper: UserDataMapper,
    private val userDetailsMapper: UserDetailsMapper,
    private val userRepositoryMapper: UserRepositoryMapper,
    private val userDetailsUiMapper: UserDetailsUiMapper,
    private val userRepositoryUiMapper: UserRepositoryUiMapper,
) {
    fun refreshUsers(): Flow<Result<Unit>> = safeApiCall {
        val usersFromApi = apiService.getUsers()
        userDao.clearAllUsers()
        userDao.insertUsers(usersFromApi.map { userDataMapper.dtoToEntity(it) })
    }

    fun getAllUsers(): Flow<List<UserEntity>> = userDao.getAllUsers()

    fun searchUsers(query: String): Flow<List<UserEntity>> = userDao.searchUsers("%$query%")

    fun getUserDetailsWithRepos(username: String): Flow<Result<Pair<UserDetailsUi, List<UserRepositoryUi>>>> =
        flow {
            emit(Result.Loading)

            try {
                coroutineScope {
                    val detailsAsync = async { apiService.getUserDetails(username) }
                    val reposAsync = async { apiService.getUserRepositories(username) }

                    val userDetailsDto = detailsAsync.await()
                    val userRepositoriesDto = reposAsync.await()

                    userDao.upsertUserDetailAndRepos(
                        details = userDetailsMapper.userDetailsDtoToEntity(userDetailsDto),
                        repos = userRepositoryMapper.userRepositoryToEntityList(
                            userRepositoriesDto,
                            username
                        )
                    )
                }
            } catch (e: Exception) {
                // Network failed, we'll rely on the DB cache
            }

            userDao.getUserDetailsWithRepos(username).collect { userDetailsWithRepos ->
                if (userDetailsWithRepos != null) {
                    val userDetailsUi =
                        userDetailsUiMapper.entityToUi(userDetailsWithRepos)
                    val userReposUi =
                        userRepositoryUiMapper.entityToUi(userDetailsWithRepos.repositories)
                    emit(Result.Success(Pair(userDetailsUi, userReposUi)))
                } else {
                    emit(Result.Error("User not found and network unavailable."))
                }
            }
        }
}
