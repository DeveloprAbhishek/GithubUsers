package com.abhishek.githubusers.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.abhishek.githubusers.ui.model.UserRepositoryUi

@Composable
fun UserRepositoryList(repositories: List<UserRepositoryUi>) {
    LazyColumn {
        items(repositories) { repo ->
            UserRepositoryListItem(repo = repo)
        }
    }
}
