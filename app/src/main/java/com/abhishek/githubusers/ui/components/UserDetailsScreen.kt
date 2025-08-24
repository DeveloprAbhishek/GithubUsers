package com.abhishek.githubusers.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abhishek.githubusers.ui.viewmodel.UserDetailsUiState

@Composable
fun UserDetailsScreen(uiState: UserDetailsUiState) {
    when (val state = uiState) {
        is UserDetailsUiState.Loading -> LoadingComposable(
            modifier = Modifier.fillMaxSize()
        )

        is UserDetailsUiState.Success -> UserDetailsContent(
            modifier = Modifier.fillMaxSize(),
            userDetails = state.userDetails,
            repositories = state.repositories
        )

        is UserDetailsUiState.Error -> ErrorComposable(
            modifier = Modifier.fillMaxSize(),
            errorMessage = state.message
        )
    }
}
