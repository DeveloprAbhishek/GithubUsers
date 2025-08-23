package com.abhishek.githubusers.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.abhishek.githubusers.ui.utils.PreviewUtils
import com.abhishek.githubusers.ui.viewmodel.UsersUiState

@Composable
fun UserListScreenComposable(
    modifier: Modifier = Modifier,
    uiState: UsersUiState,
) {
    when (val state = uiState) {
        is UsersUiState.Loading -> LoadingComposable(
            modifier = modifier
        )

        is UsersUiState.Error -> ErrorComposable(
            modifier = modifier,
            errorMessage = state.message
        )

        is UsersUiState.Success -> {
            UserListComposable(
                modifier = modifier,
                users = state.data,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreen() {
    UserListScreenComposable(
        modifier = Modifier.fillMaxSize(),
        uiState = UsersUiState.Error("Something went wrong")
    )
}

@Preview(showBackground = true)
@Composable
fun LoadingScreen() {
    UserListScreenComposable(
        modifier = Modifier.fillMaxSize(),
        uiState = UsersUiState.Loading
    )
}

@Preview(showBackground = true)
@Composable
fun SuccessScreen() {
    UserListScreenComposable(
        modifier = Modifier.fillMaxSize(),
        uiState = UsersUiState.Success(
            data = PreviewUtils.getUserList()
        )
    )
}
