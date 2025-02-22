package com.bo.victor.yaperecetas.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bo.victor.yaperecetas.model.Origin
import com.bo.victor.yaperecetas.model.Recipe
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/****
 * Project: YapeRecetas
 * From: com.bo.victor.yaperecetas.ui.screens
 * Created by: Victor Sanjines Lopez on 21/2/2025
 * More info: www.victorsanjines.com.bo
 * All rights reserved 2025
 ****/
@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreenDisplaysRecipes() {
        val recipes = listOf(
            Recipe(1, "Pizza", "https://example.com/pizza.jpg", "Pizza deliciosa", listOf("Harina", "Tomate"), Origin(40.0, -3.0))
        )

        composeTestRule.setContent {
            RecipeListScreen(recipes, onRecipeClick = {})
        }

        composeTestRule.onNodeWithText("Pizza").assertExists()
    }
}