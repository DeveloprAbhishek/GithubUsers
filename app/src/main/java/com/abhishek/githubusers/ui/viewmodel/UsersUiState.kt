package com.abhishek.githubusers.ui.viewmodel

import com.abhishek.githubusers.ui.model.UsersItemUi

sealed interface UsersUiState {
    data object Loading : UsersUiState
    data class Success(val users: List<UsersItemUi>) : UsersUiState
    data class Error(val message: String) : UsersUiState
    data class NoResults(val message: String) : UsersUiState
}
