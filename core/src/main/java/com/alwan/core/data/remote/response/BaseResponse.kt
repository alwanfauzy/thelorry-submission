package com.alwan.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("error")
    val error: String?,
    @SerializedName("data")
    val data: T?,
)