package com.alwan.thelorryresto.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alwan.core.data.remote.request.SendCommentRequest
import com.alwan.core.data.util.Resource
import com.alwan.core.domain.model.Comment
import com.alwan.core.domain.model.FoodDetail
import com.alwan.core.domain.usecase.RestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val restaurantUseCase: RestaurantUseCase) :
    ViewModel() {
    private val _comments = MutableLiveData<List<Comment>?>(null)
    val comments: LiveData<List<Comment>?> = _comments

    fun getRestaurantDetailsByID(restaurantId: Int): LiveData<Resource<FoodDetail>> =
        restaurantUseCase.getRestaurantDetailsByID(restaurantId).asLiveData()

    fun getCommentById(restaurantId: Int, page: Int): LiveData<Resource<List<Comment>>> =
        restaurantUseCase.getCommentsByRestaurantID(restaurantId, page).asLiveData()

    fun sendCommentToRestaurantByID(sendCommentRequest: SendCommentRequest): LiveData<Resource<String>> =
        restaurantUseCase.sendCommentToRestaurantByID(sendCommentRequest).asLiveData()

    fun addAllComments(comments: List<Comment>) {
        val currentComments = _comments.value
        val result = ArrayList<Comment>()

        result.addAll(currentComments.orEmpty())
        result.addAll(comments)

        _comments.postValue(result)
    }
}