package com.alwan.core.domain.usecase

import com.alwan.core.data.remote.request.CommentRequest
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

    override fun getRestaurantDetailsByID(restaurantId: String): Flow<Resource<FoodDetail>> =
        restaurantRepository.getRestaurantDetailsByID(restaurantId)

    override fun getCommentsByRestaurantID(
        restaurantId: String,
        page: Int
    ): Flow<Resource<List<Comment>>> =
        restaurantRepository.getCommentsByRestaurantID(restaurantId, page)

//    override fun sendCommentToRestaurantByID(commentRequest: CommentRequest): Flow<Resource<Boolean>> {
//        TODO("Not yet implemented")
//    }
}