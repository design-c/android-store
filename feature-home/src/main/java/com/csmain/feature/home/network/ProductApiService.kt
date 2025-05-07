package com.csmain.feature.home.network

import com.csmain.feature.home.models.Product
import com.csmain.feature.home.models.Products
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): Products
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: String): Product
}

object ProductInstance {
    private const val BASE_URL: String = "https://dummyjson.com"

    val api: ProductApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApiService::class.java)
    }

}