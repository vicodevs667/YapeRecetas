package com.bo.victor.yaperecetas.di

import com.bo.victor.yaperecetas.data.remote.ApiService
import com.bo.victor.yaperecetas.data.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/****
 * Project: YapeRecetas
 * From: com.bo.victor.yaperecetas.di
 * Created by: Victor Sanjines Lopez on 20/2/2025
 * More info: www.victorsanjines.com.bo
 * All rights reserved 2025
 ****/
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRecipeRepository(apiService: ApiService): RecipeRepository {
        return RecipeRepository(apiService)
    }
}