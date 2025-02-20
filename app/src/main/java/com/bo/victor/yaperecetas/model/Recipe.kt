package com.bo.victor.yaperecetas.model

/****
 * Project: YapeRecetas
 * From: com.bo.victor.yaperecetas.model
 * Created by: Victor Sanjines Lopez on 20/2/2025
 * More info: www.victorsanjines.com.bo
 * All rights reserved 2025
 ****/
data class Recipe(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val ingredients: List<String>,
    val origin: Origin
)
