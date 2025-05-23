package com.csmain.feature_details.data

import com.csmain.common.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductDetailsRepository {

    val product: Flow<Product>

    fun requestProductById(id: String)

}