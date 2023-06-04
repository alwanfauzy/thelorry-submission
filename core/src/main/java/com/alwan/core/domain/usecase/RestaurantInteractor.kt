package com.alwan.core.domain.usecase

import com.alwan.core.data.remote.request.SendCommentRequest
import com.alwan.core.data.util.Resource
import com.alwan.core.domain.model.Comment
import com.alwan.core.domain.model.Food
import com.alwan.core.domain.model.FoodDetail
import com.alwan.core.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantInteractor @Inject constructor(private val restaurantRepository: RestaurantRepository) :
    RestaurantUseCase {

    override fun getRestaurantListByCategory(category: String): Flow<Resource<List<Food>>> =
        restaurantRepository.getRestaurantListByCategory(category)

    override fun getRestaurantDetailsByID(restaurantId: Int): Flow<Resource<FoodDetail>> =
        restaurantRepository.getRestaurantDetailsByID(restaurantId)

    override fun getCommentsByRestaurantID(
        restaurantId: Int,
        page: Int
    ): Flow<Resource<List<Comment>>> =
        restaurantRepository.getCommentsByRestaurantID(restaurantId, page)

    override fun sendCommentToRestaurantByID(sendCommentRequest: SendCommentRequest): Flow<Resource<String>> = restaurantRepository.sendCommentToRestaurantByID(sendCommentRequest)
}