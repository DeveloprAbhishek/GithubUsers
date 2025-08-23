package com.abhishek.githubusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.abhishek.githubusers.ui.components.ErrorComposable
import com.abhishek.githubusers.ui.components.LoadingComposable
import com.abhishek.githubusers.ui.theme.GithubUsersTheme
import com.abhishek.githubusers.ui.viewmodel.UsersUiState
import com.abhishek.githubusers.ui.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubUsersTheme {
                val viewModel: UsersViewModel = hiltViewModel()
                val uiState by viewModel.usersUiState.collectAsState()

                when (val state = uiState) {
                    is UsersUiState.Loading -> LoadingComposable(
                        modifier = Modifier.fillMaxSize()
                    )

                    is UsersUiState.Error -> ErrorComposable(
                        modifier = Modifier.fillMaxSize(),
                        errorMessage = state.message
                    )

                    is UsersUiState.Success -> {

                    }
                }
            }
        }
    }
}
