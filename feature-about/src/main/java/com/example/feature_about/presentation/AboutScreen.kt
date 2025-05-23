package com.example.feature_about.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.csmain.common.ui.CommonHeader

@Composable
fun AboutScreen(
    onBackClick: () -> Unit,
) {
    About(
        onBackClick
    )
}

@Composable
fun About(
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CommonHeader(
                title = "About Us",
                onBackClick = onBackClick
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://cdn-icons-png.flaticon.com/512/1170/1170576.png", // Логотип
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(120.dp)
                    )
                    Text(
                        text = "Online Shop",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            item {
                Text(
                    text = "Welcome to Online Shop! We are committed to delivering high-quality products with exceptional service right to your doorstep.",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1E1E1E), RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Our Mission",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFFE91E63), // Тематический акцент
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "To make online shopping simple, accessible, and enjoyable for everyone.",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Text(
                        text = "Our Values",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFFE91E63),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = """
                            - Customer satisfaction above all.
                            - Transparency in all operations.
                            - Dedication to product quality.
                        """.trimIndent(),
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            item {
                Divider(
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .fillMaxWidth(),
                    color = Color.Gray,
                    thickness = 1.dp
                )
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1E1E1E), RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Contact Us",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFFE91E63),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    ContactItem(
                        icon = Icons.Default.Email,
                        title = "Email",
                        subtitle = "android-store@gmail.com"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ContactItem(
                        icon = Icons.Default.Phone,
                        title = "Phone",
                        subtitle = "+7 (800) 5555-3535"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ContactItem(
                        icon = Icons.Default.LocationOn,
                        title = "Address",
                        subtitle = "69 Online Street, Internet City"
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ContactItem(
    icon: ImageVector,
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = Color(0xFFE91E63),
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}