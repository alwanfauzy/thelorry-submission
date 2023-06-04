package com.alwan.core.data.mapper

import com.alwan.core.data.remote.response.CommentListResponse
import com.alwan.core.data.remote.response.FoodDetailResponse
import com.alwan.core.data.remote.response.FoodListResponse
import com.alwan.core.data.remote.response.SendCommentResponse
import com.alwan.core.domain.model.Address
import com.alwan.core.domain.model.Comment
import com.alwan.core.domain.model.Food
import com.alwan.core.domain.model.FoodDetail
import com.alwan.core.util.orZero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RestaurantMapper @Inject constructor() {
    fun mapFoodListResponseToFoods(from: FoodListResponse): Flow<List<Food>> =
        flowOf(from.foodList?.map {
            Food(
                id = it.id.orZero(),
                title = it.title.orEmpty(),
                image = it.image.orEmpty()
            )
        }.orEmpty())

    fun mapFoodDetailResponseToFoodDetail(from: FoodDetailResponse) = flowOf(
        FoodDetail(
            title = from.title.orEmpty(),
            images = from.images?.map { it.url.orEmpty() }.orEmpty(),
            rating = from.rating.orZero(),
            address = Address(
                fullName = from.address?.fullName.orEmpty(),
                lat = from.address?.lat.orEmpty(),
                lng = from.address?.lng.orEmpty(),
            ),
            description = from.description.orEmpty(),
        )
    )

    fun mapCommentListResponseToComments(from: CommentListResponse): Flow<List<Comment>> = flowOf(
        from.comments?.map {
            Comment(
                id = it.id.orZero(),
                userName = it.userName.orEmpty(),
                body = it.body.orEmpty(),
                profilePicture = it.profilePicture.orEmpty(),
            )
        }.orEmpty()
    )

    fun mapSendCommentResponseToString(from: SendCommentResponse): Flow<String> =
        flowOf(from.message.orEmpty())
}