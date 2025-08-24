package com.abhishek.githubusers.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abhishek.githubusers.ui.theme.Purple40
import com.abhishek.githubusers.ui.viewmodel.UserDetailsUiState
import com.abhishek.githubusers.utils.AppConstants.USERS_DETAILS

@Composable
fun UserDetailsScreen(
    uiState: UserDetailsUiState,
    onBackPress: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBarComposable(
                toolbarText = USERS_DETAILS,
                backgroundColor = Purple40,
                showBackButton = true,
                onBackPress = onBackPress
            )
        }
    ) { paddingValues ->
        when (val state = uiState) {
            is UserDetailsUiState.Loading -> LoadingComposable(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            )

            is UserDetailsUiState.Success -> UserDetailsContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                userDetails = state.userDetails,
                repositories = state.repositories
            )

            is UserDetailsUiState.Error -> ErrorComposable(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                errorMessage = state.message
            )
        }
    }
}
