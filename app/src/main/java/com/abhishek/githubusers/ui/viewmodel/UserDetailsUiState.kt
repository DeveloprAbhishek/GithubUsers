package com.abhishek.githubusers.ui.viewmodel

import com.abhishek.githubusers.ui.model.UserDetailsUi
import com.abhishek.githubusers.ui.model.UserRepositoryUi

sealed class UserDetailsUiState {
    object Loading : UserDetailsUiState()
    data class Success(
        val userDetails: UserDetailsUi,
        val repositories: List<UserRepositoryUi>
    ) : UserDetailsUiState()

    data class Error(val message: String) : UserDetailsUiState()
}
