package com.alwan.core.domain.usecase

import com.alwan.core.data.remote.request.CommentRequest
import com.alwan.core.data.util.Resource
import com.alwan.core.domain.model.Comment
import com.alwan.core.domain.model.Food
import com.alwan.core.domain.model.FoodDetail
import kotlinx.coroutines.flow.Flow

interface RestaurantUseCase {
    fun getRestaurantListByCategory(category: String): Flow<Resource<List<Food>>>

    fun getRestaurantDetailsByID(restaurantId: String): Flow<Resource<FoodDetail>>

    fun getCommentsByRestaurantID(
        restaurantId: String,
        page: Int
    ): Flow<Resource<List<Comment>>>

//    fun sendCommentToRestaurantByID(commentRequest: CommentRequest): Flow<Resource<Boolean>>
}