package com.csmain.feature_details.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.csmain.feature.home.models.Product
import com.csmain.feature_details.data.ProductDetailsRepository
import com.csmain.feature_details.navigation.DetailsScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: ProductDetailsRepository,
    private val handle: SavedStateHandle
) : ViewModel() {

    private val route = handle.toRoute<DetailsScreen>()

    sealed interface State {
        data object Loading : State
        data class Content(val product: Product) : State
    }

    val state = repository.product
        .map { State.Content(it) }
        .stateIn(viewModelScope, SharingStarted.Lazily, State.Loading)

    init {
        fetchProductById(route.id)
    }

    private fun fetchProductById(id: String) {
        repository.requestProductById(id)
    }

}
