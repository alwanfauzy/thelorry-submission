package com.alwan.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class CommentListResponse(
    @SerializedName("comments")
    val comments: ArrayList<CommentResponse>?,
)

data class CommentResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("user_name")
    val userName: String?,
    @SerializedName("body")
    val body: String?,
    @SerializedName("profile_picture")
    val profilePicture: String?,
)