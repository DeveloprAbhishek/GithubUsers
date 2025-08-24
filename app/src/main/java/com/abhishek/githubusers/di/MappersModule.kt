package com.abhishek.githubusers.di

import com.abhishek.githubusers.data.mapper.UserDataMapperImpl
import com.abhishek.githubusers.domain.mapper.UserDataMapper
import com.abhishek.githubusers.domain.mapper.UserUiMapper
import com.abhishek.githubusers.ui.mapper.UserUiMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MappersModule {

    @Binds
    abstract fun bindUserDataMapper(
        userDataMapperImpl: UserDataMapperImpl
    ): UserDataMapper


    @Binds
    abstract fun bindUserUiMapper(
        userUiMapperImpl: UserUiMapperImpl
    ): UserUiMapper
}
