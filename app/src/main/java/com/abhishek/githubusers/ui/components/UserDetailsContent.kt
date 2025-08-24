package com.abhishek.githubusers.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abhishek.githubusers.ui.model.UserDetailsUi
import com.abhishek.githubusers.ui.model.UserRepositoryUi

@Composable
fun UserDetailsContent(
    modifier: Modifier = Modifier,
    userDetails: UserDetailsUi,
    repositories: List<UserRepositoryUi>,
) {
    Column(modifier = modifier.padding(16.dp)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            UserDetailsInfo(userDetails = userDetails)
        }
        Column(modifier = Modifier.padding(top = 16.dp)) {
            UserRepositoryList(repositories = repositories)
        }
    }
}
