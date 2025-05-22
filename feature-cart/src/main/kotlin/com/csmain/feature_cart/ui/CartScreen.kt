package com.csmain.feature_cart.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.csmain.common.models.Product
import java.lang.reflect.Modifier

@Composable
fun CartScreen(
    viewModel: CartViewModel =  hiltViewModel(),
    onAddProduct: (product: Product) -> Unit,
    onRemoveProduct: (id: String) -> Unit
) {
    Cart(
        viewModel.state,
        onAddProduct,
        onRemoveProduct
    )
}

@Composable
fun Cart(
    products: List<Product>,
    onAddProduct: (product: Product) -> Unit,
    onRemoveProduct: (id: String) -> Unit
) {
    OutlinedCard() {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {  }
    }


}