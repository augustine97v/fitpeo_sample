package com.fitpeo.sample.di

import com.fitpeo.sample.ui.home.HomeRepository
import com.fitpeo.sample.ui.home.HomeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * @author Augustine on 01/03/23.
 * */
@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        homeService: HomeService
    ): HomeRepository {
        return HomeRepository(homeService)
    }
}