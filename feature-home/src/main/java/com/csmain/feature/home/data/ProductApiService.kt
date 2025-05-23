package com.csmain.feature.home.data

import com.csmain.common.models.Product
import com.csmain.feature.home.domain.Products
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): Products
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: String): Product
}