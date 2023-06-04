package com.alwan.core.domain.model

data class FoodDetail(
    val title: String,
    val images: List<String>,
    val rating: Int,
    val address: Address,
    val description: String,
)

data class Address(
    val fullName: String,
    val lat: String,
    val lng: String,
)