package com.alwan.core.data.repository

import com.alwan.core.data.mapper.RestaurantMapper
import com.alwan.core.data.remote.datasource.RestaurantRemoteDataSource
import com.alwan.core.data.remote.request.CommentRequest
import com.alwan.core.data.remote.response.CommentListResponse
import com.alwan.core.data.remote.response.FoodDetailResponse
import com.alwan.core.data.remote.response.FoodListResponse
import com.alwan.core.data.util.NetworkOnlyResource
import com.alwan.core.data.util.NetworkResult
import com.alwan.core.data.util.Resource
import com.alwan.core.domain.model.Comment
import com.alwan.core.domain.model.Food
import com.alwan.core.domain.model.FoodDetail
import com.alwan.core.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val restaurantRemoteDataSource: RestaurantRemoteDataSource,
    private val restaurantMapper: RestaurantMapper,
) : RestaurantRepository {
    override fun getRestaurantListByCategory(category: String): Flow<Resource<List<Food>>> =
        object : NetworkOnlyResource<List<Food>, FoodListResponse>() {
            override suspend fun loadFromNetwork(data: FoodListResponse): Flow<List<Food>> =
                restaurantMapper.mapFoodListResponseToFoods(data)

            override suspend fun createCall(): Flow<NetworkResult<FoodListResponse>> =
                restaurantRemoteDataSource.getRestaurantListByCategory(category)
        }.asFlow()

    override fun getRestaurantDetailsByID(restaurantId: String): Flow<Resource<FoodDetail>> =
        object : NetworkOnlyResource<FoodDetail, FoodDetailResponse>() {
            override suspend fun loadFromNetwork(data: FoodDetailResponse): Flow<FoodDetail> =
                restaurantMapper.mapFoodDetailResponseToFoodDetail(data)

            override suspend fun createCall(): Flow<NetworkResult<FoodDetailResponse>> =
                restaurantRemoteDataSource.getRestaurantDetailsByID(restaurantId)
        }.asFlow()

    override fun getCommentsByRestaurantID(
        restaurantId: String,
        page: Int
    ): Flow<Resource<List<Comment>>> =
        object : NetworkOnlyResource<List<Comment>, CommentListResponse>() {
            override suspend fun loadFromNetwork(data: CommentListResponse): Flow<List<Comment>> =
                restaurantMapper.mapCommentListResponseToComments(data)

            override suspend fun createCall(): Flow<NetworkResult<CommentListResponse>> =
                restaurantRemoteDataSource.getCommentsByRestaurantID(restaurantId, page)
        }.asFlow()

//    override fun sendCommentToRestaurantByID(commentRequest: CommentRequest): Flow<Resource<Boolean>> {
//        TODO("Not yet implemented")
//    }
}