package com.csmain.feature_cart.data

import com.csmain.common.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor() {

    private val cartState = MutableStateFlow<List<CartProduct>>(emptyList())

    fun getFlow(): MutableStateFlow<List<CartProduct>> {
        return cartState
    }

    fun addProduct(product: Product, count: Int = 1) {
        cartState.update { oldState ->
            val existingProduct = oldState.find { it.product == product }

            if (existingProduct != null) {
                oldState.map {
                    if (it.product == product) {
                        it.copy(count = it.count + count)
                    } else {
                        it
                    }
                }
            } else {
                oldState + CartProduct(product = product, count = count)
            }
        }
    }

    fun removeProduct(id: String, count: Int = 1) {
        cartState.update { oldState ->
            oldState.mapNotNull { cartProduct ->
                if (cartProduct.product.id == id) {
                    if (cartProduct.count > count) {
                        cartProduct.copy(count = cartProduct.count - count)
                    } else {
                        null
                    }
                } else {
                    cartProduct
                }
            }
        }
    }
}

data class CartProduct(
    val product: Product,
    val count: Int
)