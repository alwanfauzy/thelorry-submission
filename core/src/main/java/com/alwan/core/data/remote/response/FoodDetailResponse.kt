package com.alwan.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class FoodDetailResponse(
    @SerializedName("title")
    val title: String?,
    @SerializedName("images")
    val images: List<ImagesResponse>?,
    @SerializedName("rating")
    val rating: Int?,
    @SerializedName("address")
    val address: AddressResponse?,
    @SerializedName("description")
    val description: String?,
)

data class ImagesResponse(
    @SerializedName("url")
    val url: String?,
)

data class AddressResponse(
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("lat")
    val lat: String?,
    @SerializedName("lng")
    val lng: String?,
)