package com.alwan.thelorryresto.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alwan.core.data.util.Resource
import com.alwan.core.domain.model.Food
import com.alwan.core.domain.usecase.RestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val restaurantUseCase: RestaurantUseCase) :
    ViewModel() {

    fun getRestaurantListByCategory(category: String): LiveData<Resource<List<Food>>> =
        restaurantUseCase.getRestaurantListByCategory(category).asLiveData()
}