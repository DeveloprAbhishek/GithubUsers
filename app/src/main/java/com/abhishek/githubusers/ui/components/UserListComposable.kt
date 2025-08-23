package com.abhishek.githubusers.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.abhishek.githubusers.data.model.UsersItem

@Composable
fun UserListComposable(
    modifier: Modifier = Modifier,
    users: List<UsersItem>
) {
    val listState = rememberLazyListState()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBarComposable(
                toolbarText = "Github Users",
                backgroundColor = Color.Blue
            )
        }
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier.padding(innerPadding)
        ) {
            items(users) { user ->
                UserListItemComposable(user = user)
            }
        }
    }
}
