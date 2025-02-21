package com.bo.victor.yaperecetas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bo.victor.yaperecetas.ui.screens.DetailScreen
import com.bo.victor.yaperecetas.ui.screens.MapScreen
import com.bo.victor.yaperecetas.ui.screens.RecipeListScreen
import com.bo.victor.yaperecetas.ui.theme.YapeRecetasTheme
import com.bo.victor.yaperecetas.ui.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YapeRecetasTheme {
                val navController = rememberNavController()
                val viewModel: RecipeViewModel = hiltViewModel()
                val recipes by viewModel.recipes.collectAsStateWithLifecycle()

                LaunchedEffect(Unit) {
                    viewModel.fetchRecipes()
                }

                NavHost(navController, startDestination = "home") {
                    composable("home") {
                        RecipeListScreen(recipes = recipes) { selectedRecipe ->
                            navController.navigate("detail/${selectedRecipe.id}")
                        }
                    }
                    composable("detail/{recipeId}") { backStackEntry ->
                        val recipeId = backStackEntry.arguments?.getString("recipeId")?.toIntOrNull()
                        val recipe = recipes.find { it.id == recipeId}

                        if (recipe != null) {
                            DetailScreen(
                                recipe = recipe,
                                onBack = { navController.popBackStack() },
                                onNavigateToMap = { id -> navController.navigate("map/$id") }
                            )
                        }
                    }
                    composable("map/{recipeId}") { backStackEntry ->
                        val recipeId = backStackEntry.arguments?.getString("recipeId")?.toIntOrNull()
                        val recipe = recipes.find { it.id == recipeId }

                        if (recipe != null) {
                            MapScreen(origin = recipe.origin) {
                                navController.popBackStack()
                            }
                        } else {
                            Text("No se encontró la ubicación de la receta")
                        }
                    }
                }
            }
        }
    }
}