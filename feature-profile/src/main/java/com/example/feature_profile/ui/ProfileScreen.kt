package com.example.feature_profile.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.feature_profile.data.ProfileDataStore
import com.example.feature_profile.data.UserProfile
import com.csmain.common.ui.CommonHeader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
    context: Context,
) {
    val userProfileFlow = ProfileDataStore.getProfile(context).collectAsState(initial = null)
    val userProfile = userProfileFlow.value

    if (userProfile == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    } else {
        Profile(
            onBackClick = onBackClick,
            user = userProfile,
            onSaveProfile = { name, email, phone, address ->
                CoroutineScope(Dispatchers.IO).launch {
                    ProfileDataStore.saveProfile(context, name, email, phone, address)
                }
            }
        )
    }
}

@Composable
fun Profile(
    onBackClick: () -> Unit,
    user: UserProfile,
    onSaveProfile: (String, String, String, String) -> Unit,
) {
    var isEditing by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf(user.name) }
    var email by remember { mutableStateOf(user.email) }
    var phone by remember { mutableStateOf(user.phone) }
    var address by remember { mutableStateOf(user.address) }

    Scaffold(
        topBar = {
            CommonHeader(title = "Profile", onBackClick = onBackClick)
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://cdn-icons-png.flaticon.com/512/3135/3135715.png",
                        contentDescription = "User Avatar",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    )
                    Text(
                        text = if (!isEditing && user.name.isEmpty()) {
                            "Welcome! Please set up your profile."
                        } else {
                            "Welcome Back, ${name.ifEmpty { "User" }}!"
                        },
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CustomTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = "Name",
                        placeholder = "Enter your name",
                        enabled = isEditing
                    )

                    CustomTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = "Email",
                        placeholder = "Enter your email",
                        enabled = isEditing
                    )

                    CustomTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = "Phone",
                        placeholder = "Enter your phone number",
                        enabled = isEditing
                    )

                    CustomTextField(
                        value = address,
                        onValueChange = { address = it },
                        label = "Address",
                        placeholder = "Enter your address",
                        enabled = isEditing
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            if (isEditing) {
                                onSaveProfile(name, email, phone, address)
                            }
                            isEditing = !isEditing
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isEditing) Color(0xFF4CAF50) else Color(0xFFE91E63),
                            contentColor = Color.White
                        )
                    ) {
                        Text(if (isEditing) "Save" else "Edit")
                    }

                    if (isEditing) {
                        Button(
                            onClick = {
                                name = user.name
                                email = user.email
                                phone = user.phone
                                address = user.address
                                isEditing = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Gray,
                                contentColor = Color.White
                            )
                        ) {
                            Text("Cancel")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    enabled: Boolean
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = Color.White,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray, RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = TextStyle(
                    color = if (enabled) Color.White else Color.Gray,
                    fontSize = 16.sp
                ),
                decorationBox = { innerTextField ->
                    if (value.isEmpty() && enabled) {
                        Text(
                            text = placeholder,
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                enabled = enabled
            )
        }
    }
}