package com.abhishek.githubusers.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abhishek.githubusers.ui.model.UsersItemUi

@Composable
fun UserListComposable(
    modifier: Modifier = Modifier,
    users: List<UsersItemUi>,
    onUserItemClick: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(users) { user ->
            UserListItemComposable(
                user = user,
                onUserItemClick = onUserItemClick
            )
        }
    }
}
