package com.alwan.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class SendCommentResponse(
    @SerializedName("restaurantId")
    val restaurantId: Int?,
    @SerializedName("message")
    val message: String?,
)
