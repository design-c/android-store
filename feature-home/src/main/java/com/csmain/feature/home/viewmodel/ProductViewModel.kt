package com.csmain.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csmain.common.models.Product
import com.csmain.feature.home.data.ProductRepository
import com.csmain.common.models.Products
import com.csmain.feature_cart.data.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    sealed interface State {
        data object Loading : State
        data class Content(val list: Products) : State
    }

    val state = repository.products
        .map { State.Content(it) }
        .stateIn(viewModelScope, SharingStarted.Lazily, State.Loading)

    val stateCart = cartRepository.getFlow()

    init {
        fetchProducts()
    }

    fun addToCart(product: Product) {
        cartRepository.addProduct(product)
    }

    private fun fetchProducts() {
        repository.requestProducts()
    }
}