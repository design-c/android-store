package com.csmain.feature_cart.data

import com.csmain.common.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor() {

    private val cartState = MutableStateFlow<List<Product>>(emptyList())

    public fun addCart(cart: List<Product>) {
        cartState.tryEmit(cart)
    }
    
    public fun addProduct(product: Product) {
        cartState.update { it + product }
    }

    public fun removeProduct(id: String) {
        cartState.update { list -> list.filter { id != it.id } }
    }
}