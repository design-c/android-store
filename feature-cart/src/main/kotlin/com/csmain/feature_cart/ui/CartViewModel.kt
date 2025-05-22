package com.csmain.feature_cart.ui

import androidx.lifecycle.ViewModel
import com.csmain.common.models.Product
import com.csmain.feature_cart.data.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {

    public fun addProduct(product: Product) {
        repository.addProduct(product)
    }
}
