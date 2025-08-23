package com.abhishek.githubusers.di

import com.abhishek.githubusers.data.network.ApiService
import com.abhishek.githubusers.data.repository.UsersRepository
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
    fun provideUsersRepository(apiService: ApiService): UsersRepository {
        return UsersRepository(apiService)
    }
}
