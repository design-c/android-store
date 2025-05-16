package com.csmain.feature.home.data

import android.util.Log
import com.csmain.common.models.Products
import com.csmain.feature.home.network.ProductApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ProductRepository {

    val products: Flow<Products>

    fun requestProducts()
}

class ProductRepositoryImpl @Inject constructor(
    private val coroutineScope: CoroutineScope,
    private val productApiService: ProductApiService
) : ProductRepository {

    override val products = MutableSharedFlow<Products>(replay = 1)

    override fun requestProducts() {
        coroutineScope.launch {
            val result = runCatching { productApiService.getProducts() }

            result
                .onSuccess { list -> products.emit(list) }
                .onFailure { Log.e("TAG", "ERROR", it) }
        }
    }
}