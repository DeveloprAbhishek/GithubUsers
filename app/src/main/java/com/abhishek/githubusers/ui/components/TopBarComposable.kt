package com.abhishek.githubusers.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComposable(
    toolbarText: String,
    backgroundColor: Color,
    showBackButton: Boolean = false,
    onBackPress: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Row(
                modifier =
                    Modifier
                        .offset(x = (-10).dp)
                        .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(showBackButton) {
                    IconButton(onClick = { onBackPress() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }

                Text(
                    text = toolbarText,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W700
                    ),
                    color = Color.White
                )
            }
        },
        actions = {},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.White
        ),
        modifier = Modifier
            .background(backgroundColor)
    )
}
