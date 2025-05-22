package com.example.feature_profile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.csmain.common.ui.CommonHeader

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
) {
    Profile(
        onBackClick
    )
}

@Composable
fun Profile(
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = { CommonHeader(onBackClick = onBackClick) }
    ) { padding ->
        LazyColumn(
            contentPadding = padding
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://cdn-icons-png.flaticon.com/512/3135/3135715.png",
                        contentDescription = null
                    )
                }
            }
        }
    }
}

