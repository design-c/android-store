package com.csmain.feature.home.models

data class Product(
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val price: Float,
    val discountPercentage: Float,
    val rating: Float,
    val stock: Float,
    val tags: List<String>,
    val brand: String?,
    val sku: String,
    val weight: String,
    val dimensions: Dimensions,
    val warrantyInformation: String,
    val shippingInformation: String,
    val availabilityStatus: String,
    val reviews: List<Reviews>,
    val returnPolicy: String,
    val minimumOrderQuantity: Float,
    val meta: Meta,
    val images: List<String>,
    val thumbnail: String
)