package com.bo.victor.yaperecetas.data.remote

import com.bo.victor.yaperecetas.model.Recipe
import retrofit2.http.GET

/****
 * Project: YapeRecetas
 * From: com.bo.victor.yaperecetas.data.remote
 * Created by: Victor Sanjines Lopez on 20/2/2025
 * More info: www.victorsanjines.com.bo
 * All rights reserved 2025
 ****/

interface ApiService {
    @GET("recipes")
    suspend fun getRecipes(): List<Recipe>
}