package com.abhishek.githubusers.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.abhishek.githubusers.ui.model.UserDetailsUi

@Composable
fun UserDetailsInfo(userDetails: UserDetailsUi) {
    Column {
        Row {
            AsyncImage(
                model = userDetails.avatarUrl,
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(text = userDetails.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = userDetails.login)
                Text(text = "Repositories: ${userDetails.publicRepos}")
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        userDetails.bio?.let { Text(text = userDetails.bio) }
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = "Followers: ${userDetails.followers} | Following: ${userDetails.following}")
        userDetails.company?.let { Text(text = "Company: ${userDetails.company}") }
        userDetails.location?.let { Text(text = "Location: ${userDetails.location}") }
        userDetails.email?.let { Text(text = "Email: ${userDetails.email}") }
        userDetails.blog?.let { Text(text = "Blog: ${userDetails.blog}") }
    }
}
