package com.abhishek.githubusers.di

import com.abhishek.githubusers.data.mapper.UserDataMapperImpl
import com.abhishek.githubusers.domain.mapper.UserDataMapper
import com.abhishek.githubusers.domain.mapper.UserDetailsMapper
import com.abhishek.githubusers.domain.mapper.UserRepositoryMapper
import com.abhishek.githubusers.domain.mapper.UserUiMapper
import com.abhishek.githubusers.ui.mapper.UserUiMapperImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MappersModule {

    @Binds
    abstract fun bindsUserUiMapper(impl: UserUiMapperImpl): UserUiMapper

    @Binds
    abstract fun bindsUserDataMapper(impl: UserDataMapperImpl): UserDataMapper

}

@Module
@InstallIn(SingletonComponent::class)
object MapperObjectModule {
    @Provides
    fun provideUserDetailsMapper(): UserDetailsMapper {
        return UserDetailsMapper()
    }

    @Provides
    fun provideUserRepositoryMapper(): UserRepositoryMapper {
        return UserRepositoryMapper()
    }
}
