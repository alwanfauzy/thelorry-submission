package com.alwan.core.domain.repository

import com.alwan.core.data.remote.request.SendCommentRequest
import com.alwan.core.data.util.Resource
import com.alwan.core.domain.model.Comment
import com.alwan.core.domain.model.Food
import com.alwan.core.domain.model.FoodDetail
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {
    fun getRestaurantListByCategory(category: String): Flow<Resource<List<Food>>>

    fun getRestaurantDetailsByID(restaurantId: Int): Flow<Resource<FoodDetail>>

    fun getCommentsByRestaurantID(
        restaurantId: Int,
        page: Int,
    ): Flow<Resource<List<Comment>>>

    fun sendCommentToRestaurantByID(sendCommentRequest: SendCommentRequest): Flow<Resource<String>>
}