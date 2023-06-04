package com.alwan.thelorryresto.di

import com.alwan.core.domain.usecase.RestaurantInteractor
import com.alwan.core.domain.usecase.RestaurantUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideRestaurantUseCase(restaurantInteractor: RestaurantInteractor): RestaurantUseCase
}