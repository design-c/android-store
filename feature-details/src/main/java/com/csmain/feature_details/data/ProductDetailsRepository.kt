package com.csmain.feature_details.data

import android.util.Log
import com.csmain.common.models.Product
import com.csmain.feature.home.data.ProductApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ProductDetailsRepository {

    val product: Flow<Product>

    fun requestProductById(id: String)

}

class ProductDetailsRepositoryImpl @Inject constructor(
    private val coroutineScope: CoroutineScope,
    private val productApiService: ProductApiService
) : ProductDetailsRepository {

    override val product = MutableSharedFlow<Product>(replay = 1)

    override fun requestProductById(id: String) {
        coroutineScope.launch {
            val result = runCatching { productApiService.getProductById(id) }

            result
                .onSuccess { data -> product.emit(data) }
                .onFailure { Log.e("TAG", "ERROR", it) }
        }
    }
}