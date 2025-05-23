package com.csmain.common.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonHeader(
    title: String = "Online Shop",
    onTitleClick: (() -> Unit)? = null,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    TopAppBar(
        title = {
            if (onTitleClick != null) {
                ClickableText(
                    text = AnnotatedString(title),
                    onClick = { onTitleClick() },
                    style = MaterialTheme.typography.titleLarge.copy(color = LocalContentColor.current)
                )
            } else {
                Text(text = title)
            }
        },
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            actions?.invoke(this)
        }
    )
}