package com.alwan.core.data.remote.apiservice

import com.alwan.core.data.remote.request.CommentRequest
import com.alwan.core.data.remote.response.BaseResponse
import com.alwan.core.data.remote.response.CommentListResponse
import com.alwan.core.data.remote.response.FoodDetailResponse
import com.alwan.core.data.remote.response.FoodListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RestaurantApiService {
    @GET("restaurants/category/{category}")
    suspend fun getRestaurantListByCategory(@Path("category") category: String): BaseResponse<FoodListResponse>

    @GET("restaurants/{restaurantId}")
    suspend fun getRestaurantDetailsByID(@Path("restaurantId") restaurantId: String): BaseResponse<FoodDetailResponse>

    @GET("restaurants/{restaurantId}/comments?page={page}")
    suspend fun getCommentsByRestaurantID(
        @Path("restaurantId") restaurantId: String,
        @Path("page") page: Int
    ): BaseResponse<CommentListResponse>

//    @POST("comments")
//    suspend fun sendCommentToRestaurantByID(
//        @Body commentRequest: CommentRequest,
//    ): BaseResponse<Boolean>
}