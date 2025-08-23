package com.abhishek.githubusers.ui.viewmodel

import com.abhishek.githubusers.data.model.UsersItem

sealed interface UsersUiState {
    data object Loading : UsersUiState
    data class Success(val users: List<UsersItem>) : UsersUiState
    data class Error(val message: String) : UsersUiState
}
