package com.abhishek.githubusers.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.abhishek.githubusers.ui.model.UserRepositoryUi

@Composable
fun UserRepositoryListItem(repo: UserRepositoryUi) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Text(text = repo.name, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(4.dp))
                if (repo.isPrivate) Text(text = "(Private)", fontWeight = FontWeight.Bold)
                else Text(text = "(Public)", fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(4.dp))
            repo.description?.let { Text(text = repo.description) }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Icon(imageVector = Icons.Default.Star, contentDescription = "Stars")
                Text(text = "${repo.stargazersCount}")
                Text(text = " | ")
                Text(text = "Forks: ${repo.forksCount}")
            }
        }
    }
}
