package com.bo.victor.yaperecetas.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bo.victor.yaperecetas.data.repository.RecipeRepository
import com.bo.victor.yaperecetas.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/****
 * Project: YapeRecetas
 * From: com.bo.victor.yaperecetas.ui.viewmodel
 * Created by: Victor Sanjines Lopez on 20/2/2025
 * More info: www.victorsanjines.com.bo
 * All rights reserved 2025
 ****/
@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository
): ViewModel() {

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes

    fun fetchRecipes() {
        viewModelScope.launch {
            val data = repository.getRecipes()
            println("Recetas obtenidas en UI: ${data.size}")
            _recipes.value = data
        }
    }
}