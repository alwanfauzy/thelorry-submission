package com.alwan.core.data.remote.request

import com.google.gson.annotations.SerializedName

data class SendCommentRequest(
    @SerializedName("restaurantId")
    val restaurantId: Int,

    @SerializedName("message")
    val message: String,
)