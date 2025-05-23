package com.csmain.feature.home.presentation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.csmain.common.models.Product
import com.csmain.common.ui.CommonHeader
import com.csmain.common.ui.LoadingAnimation
import com.csmain.common.ui.theme.White80
import com.csmain.feature_cart.data.CartProduct

@Composable
fun HomeScreen(
    onProductClick: (id: String) -> Unit,
    viewModel: ProductViewModel = hiltViewModel(),
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    val stateCart by viewModel.stateCart.collectAsState()

    when (val state = state) {
        ProductViewModel.State.Loading -> {
            LoadingAnimation()
        }

        is ProductViewModel.State.Content -> {
            Content(
                products = state.list.products,
                onProductClick = onProductClick,
                onCartClick = onCartClick,
                onProfileClick = onProfileClick,
                onAddProduct = { viewModel.addToCart(it) },
                onSearchQueryChanged = { viewModel.updateSearchQuery(it) },
                stateCart = stateCart,
                onAboutClick = onAboutClick,
            )
        }
    }
}


@Composable
fun Content(
    products: List<Product>,
    onProductClick: (id: String) -> Unit,
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit,
    onAboutClick: () -> Unit,
    onAddProduct: (product: Product) -> Unit,
    stateCart: List<CartProduct>,
    onSearchQueryChanged: (String) -> Unit,
) {
    var isSearchVisible by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        isSearchVisible = false
        searchQuery = ""
        onSearchQueryChanged("")
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            CommonHeader(
                actions = {
                    IconButton(onClick = onProfileClick) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Profile")
                    }
                    IconButton(onClick = onCartClick) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                    }
                    IconButton(onClick = { isSearchVisible = !isSearchVisible }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                },
                onTitleClick = onAboutClick
            )

            AnimatedVisibility(
                visible = isSearchVisible,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                SearchBar(
                    query = searchQuery,
                    onQueryChanged = {
                        searchQuery = it
                        onSearchQueryChanged(it)
                    },
                )
            }

            // Список товаров
            LazyColumn(
                contentPadding = PaddingValues(
                    top = paddingValues.calculateTopPadding(),
                    start = 16.dp,
                    end = 16.dp,
                    bottom = paddingValues.calculateBottomPadding()
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(products, key = { it.id }) {
                    ProductItem(
                        product = it,
                        onClick = { onProductClick(it.id) },
                        count = stateCart.find { p -> p.product.id === it.id }?.count ?: 0,
                        onAddProduct = { onAddProduct(it) }
                    )
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(White80, RoundedCornerShape(12.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            BasicTextField(
                value = query,
                onValueChange = onQueryChanged,
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                decorationBox = { innerTextField ->
                    if (query.isEmpty()) {
                        Text(
                            text = "Search products...",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            )

            if (query.isNotEmpty()) {
                IconButton(onClick = {
                    onQueryChanged("")
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear search",
                        tint = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    count: Int,
    onClick: () -> Unit,
    onAddProduct: (product: Product) -> Unit
) {
    Log.i("ERROR", "${product}")
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier.height(120.dp),
                model = product.images[0],
                contentDescription = null
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = count.toString(),
                        fontSize = 18.sp
                    )
                    IconButton(onClick = { onAddProduct(product) }) {
                        Icon(
                            Icons.Default.Add,
                            null
                        )
                    }
                }
            }
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