package com.abhishek.githubusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhishek.githubusers.ui.components.UserListScreenComposable
import com.abhishek.githubusers.ui.theme.GithubUsersTheme
import com.abhishek.githubusers.ui.theme.Purple40
import com.abhishek.githubusers.ui.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(scrim = Purple40.value.toInt()))
        setContent {
            GithubUsersTheme {
                val viewModel: UsersViewModel = hiltViewModel()
                val uiState by viewModel.usersUiState.collectAsStateWithLifecycle()
                val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

                UserListScreenComposable(
                    modifier = Modifier.fillMaxSize(),
                    uiState = uiState,
                    searchQuery = searchQuery,
                    onSearchQueryChanged = viewModel::onSearchQueryChanged,
                    onUserItemClick = ::onUserItemClick,
                    onClearSearchQuery = viewModel::onClearSearch
                )
            }
        }
    }

    private fun onUserItemClick(userName: String) {
        startActivity(UserDetailsActivity.createIntent(this, userName))
    }
}
