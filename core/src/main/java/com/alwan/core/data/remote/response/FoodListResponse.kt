package com.alwan.core.data.remote.response

import com.google.gson.annotations.SerializedName


data class FoodListResponse(
    @SerializedName("food_list")
    val foodList: List<FoodResponse>?,
)

data class FoodResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("image")
    val image: String?,
)

