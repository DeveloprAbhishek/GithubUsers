package com.abhishek.githubusers

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.abhishek.githubusers.ui.components.UserDetailsScreen
import com.abhishek.githubusers.ui.theme.GithubUsersTheme
import com.abhishek.githubusers.ui.viewmodel.UserDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsActivity : ComponentActivity() {

    private val viewModel: UserDetailsViewModel by viewModels()

    companion object {
        const val USER_NAME = "user_name"
        fun createIntent(
            activity: ComponentActivity,
            userName: String
        ): Intent {
            return Intent(activity, UserDetailsActivity::class.java)
                .putExtra(USER_NAME, userName)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState by viewModel.uiState.collectAsState()
            GithubUsersTheme {
                UserDetailsScreen(uiState)
            }
        }
    }
}
