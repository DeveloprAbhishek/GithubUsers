package com.abhishek.githubusers.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhishek.githubusers.ui.utils.PreviewUtils
import com.abhishek.githubusers.ui.viewmodel.UsersUiState

@Composable
fun UserListScreenComposable(
    modifier: Modifier = Modifier,
    uiState: UsersUiState,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBarComposable(
                toolbarText = "Github Users",
                backgroundColor = Color.Blue
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White),
                value = searchQuery,
                onValueChange = onSearchQueryChanged,
                placeholder = { Text(text = "Search Users") }
            )

            when (val state = uiState) {
                is UsersUiState.Loading -> LoadingComposable(
                    modifier = Modifier.fillMaxSize()
                )

                is UsersUiState.Error -> ErrorComposable(
                    modifier = Modifier.fillMaxSize(),
                    errorMessage = state.message
                )

                is UsersUiState.Success -> {
                    UserListComposable(
                        modifier = Modifier.fillMaxSize(),
                        users = state.users,
                    )
                }

                is UsersUiState.NoResults -> {
                    NoResultsComposable(
                        modifier = Modifier.fillMaxSize(),
                        message = state.message
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreen() {
    UserListScreenComposable(
        modifier = Modifier.fillMaxSize(),
        uiState = UsersUiState.Error("Something went wrong"),
        searchQuery = "",
        onSearchQueryChanged = {}
    )
}

@Preview(showBackground = true)
@Composable
fun LoadingScreen() {
    UserListScreenComposable(
        modifier = Modifier.fillMaxSize(),
        uiState = UsersUiState.Loading,
        searchQuery = "",
        onSearchQueryChanged = {}
    )
}

@Preview(showBackground = true)
@Composable
fun SuccessScreen() {
    UserListScreenComposable(
        modifier = Modifier.fillMaxSize(),
        uiState = UsersUiState.Success(
            users = PreviewUtils.getUserItemList()
        ),
        searchQuery = "",
        onSearchQueryChanged = {}
    )
}
