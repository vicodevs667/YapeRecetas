package com.bo.victor.yaperecetas.data.repository

import com.bo.victor.yaperecetas.data.remote.ApiService
import com.bo.victor.yaperecetas.model.Recipe
import javax.inject.Inject

/****
 * Project: YapeRecetas
 * From: com.bo.victor.yaperecetas.data.repository
 * Created by: Victor Sanjines Lopez on 20/2/2025
 * More info: www.victorsanjines.com.bo
 * All rights reserved 2025
 ****/
class RecipeRepository @Inject constructor(
    private val apiService: ApiService) {
    suspend fun getRecipes(): List<Recipe> {
        return try {
            apiService.getRecipes()
        } catch (e: Exception) {
            emptyList()
        }
    }

}