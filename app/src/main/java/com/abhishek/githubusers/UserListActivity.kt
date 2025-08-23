package com.abhishek.githubusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.abhishek.githubusers.ui.components.UserListScreenComposable
import com.abhishek.githubusers.ui.theme.GithubUsersTheme
import com.abhishek.githubusers.ui.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubUsersTheme {
                val viewModel: UsersViewModel = hiltViewModel()
                val uiState by viewModel.usersUiState.collectAsState()
                UserListScreenComposable(
                    modifier = Modifier.fillMaxSize(),
                    uiState = uiState
                )
            }
        }
    }
}
