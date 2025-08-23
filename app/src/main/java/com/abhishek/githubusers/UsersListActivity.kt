package com.abhishek.githubusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
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
                    is UsersUiState.Loading -> {}

                    is UsersUiState.Error -> {}

                    is UsersUiState.Success -> {}
                }
            }
        }
    }
}
