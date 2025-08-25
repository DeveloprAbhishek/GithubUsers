package com.abhishek.githubusers.di

import com.abhishek.githubusers.data.mapper.UserDataMapperImpl
import com.abhishek.githubusers.data.mapper.UserDetailsMapperImpl
import com.abhishek.githubusers.data.mapper.UserRepositoryMapperImpl
import com.abhishek.githubusers.domain.mapper.UserDataMapper
import com.abhishek.githubusers.domain.mapper.UserDetailsMapper
import com.abhishek.githubusers.domain.mapper.UserDetailsUiMapper
import com.abhishek.githubusers.domain.mapper.UserRepositoryMapper
import com.abhishek.githubusers.domain.mapper.UserRepositoryUiMapper
import com.abhishek.githubusers.domain.mapper.UserUiMapper
import com.abhishek.githubusers.ui.mapper.UserDetailsUiMapperImpl
import com.abhishek.githubusers.ui.mapper.UserRepositoryUiMapperImpl
import com.abhishek.githubusers.ui.mapper.UserUiMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MappersModule {

    @Binds
    abstract fun bindsUserUiMapper(userUiMapperImpl: UserUiMapperImpl): UserUiMapper

    @Binds
    abstract fun bindsUserDataMapper(userDataMapperImpl: UserDataMapperImpl): UserDataMapper

    @Binds
    abstract fun bindsUserDetailsUiMapper(userDetailsUiMapperImpl: UserDetailsUiMapperImpl): UserDetailsUiMapper

    @Binds
    abstract fun bindsUserDetailsDataMapper(userDetailsMapperImpl: UserDetailsMapperImpl): UserDetailsMapper

    @Binds
    abstract fun bindsUserRepositoryUiMapper(userRepositoryUiMapperImpl: UserRepositoryUiMapperImpl): UserRepositoryUiMapper

    @Binds
    abstract fun bindsUserRepositoryDataMapper(userRepositoryMapperImpl: UserRepositoryMapperImpl): UserRepositoryMapper
}
