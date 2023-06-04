package com.alwan.core.di

import com.alwan.core.data.repository.RestaurantRepositoryImpl
import com.alwan.core.domain.repository.RestaurantRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRestaurantRepository(restaurantRepository: RestaurantRepositoryImpl): RestaurantRepository
}