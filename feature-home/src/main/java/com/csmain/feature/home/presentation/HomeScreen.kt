package com.csmain.feature.home.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.csmain.common.models.Product
import com.csmain.feature.home.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    onProductClick: (id: String) -> Unit,
    viewModel: ProductViewModel = hiltViewModel(),
    onCartClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    when (val state = state) {
        ProductViewModel.State.Loading -> {
            LoadingAnimation()
        }
        is ProductViewModel.State.Content -> {
            Content(
                products = state.list.products,
                onProductClick = onProductClick,
                onCartClick = onCartClick,
            )
        }
    }
}

@Composable
fun LoadingAnimation() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            androidx.compose.material3.CircularProgressIndicator(
                color = Color(0xFF6200EE),
                strokeWidth = 4.dp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Загрузка...",
                fontSize = 16.sp,
                color = Color.Gray,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(
    products: List<Product>,
    onProductClick: (id: String) -> Unit,
    onCartClick: () -> Unit,
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {Text("Troe V Popke Shop")},
                actions = { IconButton(onClick = onCartClick) { Icon(Icons.Default.ShoppingCart, null) } }
            )
        }
    ){ padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products, key = { it.id }) {
                ProductItem(
                    product = it,
                    onClick = { onProductClick(it.id) }
                )
            }
        }
    }

}

@Composable
fun ProductItem(
    product: Product,
    onClick: () -> Unit
) {
    Log.i("ERROR", "${product}")
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier.height(120.dp),
                model = product.images[0],
                contentDescription = null
            )
            Spacer(Modifier.height(16.dp))
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
                )
                Text(
                    text = product.category,
                )
            }
        }
    }
}

//@Preview
//@Composable
//private fun HomePreview() {
//    FinalProjectTheme {
//        Content(listOf(Product(
//            id = "a", title = "asdf",
//            description = "woooow",
//            category = "woooow",
//            price = 345.0f,
//            discountPercentage = 345.0f,
//            rating = 345.0f,
//            stock = 345.0f,
//            tags = emptyList(),
//            brand = "woooow",
//            sku = "woooow",
//            weight = "woooow",
//            dimensions = (),
//            warrantyInformation = "woooow",
//            shippingInformation = "woooow",
//            availabilityStatus = "woooow",
//            reviews = 345.0f,
//            returnPolicy = "woooow",
//            minimumOrderQuantity = 345.0f,
//            meta = 345.0f,
//            images = 345.0f,
//            thumbnail = "woooow",
//        ))) { }
//    }
//}