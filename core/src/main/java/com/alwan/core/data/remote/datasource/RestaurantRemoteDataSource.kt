package com.alwan.core.data.remote.datasource

import android.util.Log
import com.alwan.core.data.remote.apiservice.RestaurantApiService
import com.alwan.core.data.remote.request.CommentRequest
import com.alwan.core.data.remote.response.BaseResponse
import com.alwan.core.data.remote.response.CommentListResponse
import com.alwan.core.data.remote.response.FoodDetailResponse
import com.alwan.core.data.remote.response.FoodListResponse
import com.alwan.core.data.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

class RestaurantRemoteDataSource @Inject constructor(private val restaurantApiService: RestaurantApiService) {

    suspend fun getRestaurantListByCategory(category: String): Flow<NetworkResult<FoodListResponse>> =
        handleApi { restaurantApiService.getRestaurantListByCategory(category) }

    suspend fun getRestaurantDetailsByID(restaurantId: String): Flow<NetworkResult<FoodDetailResponse>> =
        handleApi { restaurantApiService.getRestaurantDetailsByID(restaurantId) }

    suspend fun getCommentsByRestaurantID(
        restaurantId: String,
        page: Int
    ): Flow<NetworkResult<CommentListResponse>> =
        handleApi { restaurantApiService.getCommentsByRestaurantID(restaurantId, page) }

//    suspend fun sendCommentToRestaurantByID(commentRequest: CommentRequest) =
//        handleApi { restaurantApiService.sendCommentToRestaurantByID(commentRequest) }
}

suspend fun <T : Any> handleApi(
    execute: suspend () -> BaseResponse<T>
): Flow<NetworkResult<T>> = flow {
    lateinit var response: BaseResponse<T>

    try {
        response = execute()
        val data = response.data

        if (data != null)
            emit(NetworkResult.Success(data))
    } catch (e: HttpException) {
        val errorJsonObject =
            e.response()?.errorBody()?.charStream()?.readText()?.let { JSONObject(it) }
        val message = errorJsonObject?.getString("message")

        emit(NetworkResult.Error(message ?: "HttpException"))
    } catch (e: Throwable) {
        emit(NetworkResult.Exception(e))
    }
}.flowOn(Dispatchers.IO)