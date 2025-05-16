package com.csmain.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csmain.feature.home.data.ProductRepository
import com.csmain.common.models.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository,
) : ViewModel() {

    sealed interface State {
        data object Loading : State
        data class Content(val list: Products) : State
    }

    val state = repository.products
        .map { State.Content(it) }
        .stateIn(viewModelScope, SharingStarted.Lazily, State.Loading)

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        repository.requestProducts()
    }
}