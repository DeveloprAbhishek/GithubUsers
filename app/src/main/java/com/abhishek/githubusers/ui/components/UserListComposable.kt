package com.abhishek.githubusers.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.abhishek.githubusers.data.model.UsersItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserListComposable(
    modifier: Modifier = Modifier,
    users: List<UsersItem>,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit
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
            stickyHeader {
                TextField(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    value = searchQuery,
                    onValueChange = onSearchQueryChanged,
                    placeholder = { Text(text = "Search Users") }
                )
            }
            items(users) { user ->
                UserListItemComposable(user = user)
            }
        }
    }
}
