package com.csmain.feature_details.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.csmain.common.models.Product
import com.csmain.common.ui.CommonHeader
import com.csmain.common.ui.LoadingAnimation

@Composable
fun DetailsScreen(
    onBackClick: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel(),
    onAboutClick: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    when (val state = state) {
        DetailsViewModel.State.Loading -> {
            LoadingAnimation()
        }

        is DetailsViewModel.State.Content -> {
            ProductCard(
                product = state.product,
                onBackClick = onBackClick,
                onAboutClick = onAboutClick,
            )
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    onBackClick: () -> Unit,
    onAboutClick: () -> Unit,
    ) {
    Scaffold(
        topBar = { CommonHeader(onBackClick = onBackClick, onTitleClick = onAboutClick)  }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .height(240.dp)
                        .align(Alignment.CenterHorizontally),
                    model = product.images[0],
                    contentDescription = null
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Title:",
                        fontSize = 18.sp
                    )
                    Text(
                        text = product.title,
                        fontSize = 18.sp
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Category:",
                        fontSize = 18.sp
                    )
                    Text(
                        text = product.category,
                        fontSize = 18.sp
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Brand:",
                        fontSize = 18.sp
                    )
                    Text(
                        text = product.brand.orEmpty(),
                        fontSize = 18.sp
                    )
                }
                Spacer(Modifier.height(32.dp))
                Text(
                    text = product.description,
                    fontSize = 18.sp
                )
            }
        }
    }
}