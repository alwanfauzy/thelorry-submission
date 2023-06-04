package com.alwan.core.data.remote.apiservice

import com.alwan.core.data.remote.request.SendCommentRequest
import com.alwan.core.data.remote.response.*
import retrofit2.http.*

interface RestaurantApiService {
    @GET("restaurants/category/{category}")
    suspend fun getRestaurantListByCategory(@Path("category") category: String): BaseResponse<FoodListResponse>

    @GET("restaurants/{restaurantId}")
    suspend fun getRestaurantDetailsByID(@Path("restaurantId") restaurantId: Int): BaseResponse<FoodDetailResponse>

    @GET("restaurants/{restaurantId}/comments")
    suspend fun getCommentsByRestaurantID(
        @Path("restaurantId") restaurantId: Int,
        @Query("page") page: Int
    ): BaseResponse<CommentListResponse>

    @POST("comments")
    suspend fun sendCommentToRestaurantByID(
        @Body sendCommentRequest: SendCommentRequest,
    ): BaseResponse<SendCommentResponse>
}