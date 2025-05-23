package com.csmain.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csmain.common.models.Product
import com.csmain.feature.home.domain.ProductRepository
import com.csmain.feature.home.domain.Products
import com.csmain.feature_cart.data.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
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

    private val searchQuery = MutableStateFlow("")

    val state = searchQuery
        .combine(repository.products) { query, products ->
            val filteredProducts = if (query.isEmpty()) {
                products.products
            } else {
                products.products.filter {
                    it.title.contains(query, ignoreCase = true) ||
                            it.category.contains(query, ignoreCase = true)
                }
            }
            State.Content(Products(filteredProducts))
        }
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

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }
}