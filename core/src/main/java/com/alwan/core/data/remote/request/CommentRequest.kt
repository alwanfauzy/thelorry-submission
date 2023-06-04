package com.alwan.core.data.remote.request

import com.google.gson.annotations.SerializedName

data class CommentRequest(
    @SerializedName("restaurantId")
    val restaurantId: String,

    @SerializedName("message")
    val message: String,
)