package com.abhishek.githubusers.di

import com.abhishek.githubusers.data.db.UserDao
import com.abhishek.githubusers.data.network.ApiService
import com.abhishek.githubusers.data.repository.UsersRepository
import com.abhishek.githubusers.domain.mapper.UserDataMapper
import com.abhishek.githubusers.domain.mapper.UserDetailsMapper
import com.abhishek.githubusers.domain.mapper.UserDetailsUiMapper
import com.abhishek.githubusers.domain.mapper.UserRepositoryMapper
import com.abhishek.githubusers.domain.mapper.UserRepositoryUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUsersRepository(
        apiService: ApiService,
        userDao: UserDao,
        userDataMapper: UserDataMapper,
        userDetailsMapper: UserDetailsMapper,
        userRepositoryMapper: UserRepositoryMapper,
        userDetailsUiMapper: UserDetailsUiMapper,
        userRepositoryUiMapper: UserRepositoryUiMapper,
    ): UsersRepository {
        return UsersRepository(
            apiService,
            userDao,
            userDataMapper,
            userDetailsMapper,
            userRepositoryMapper,
            userDetailsUiMapper,
            userRepositoryUiMapper,
        )
    }
}
