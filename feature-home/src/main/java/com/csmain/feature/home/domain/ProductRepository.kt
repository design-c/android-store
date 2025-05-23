package com.csmain.feature.home.domain

import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    val products: Flow<Products>

    fun requestProducts()
}

