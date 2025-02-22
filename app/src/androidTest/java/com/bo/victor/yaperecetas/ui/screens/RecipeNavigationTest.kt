package com.bo.victor.yaperecetas.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bo.victor.yaperecetas.model.Origin
import com.bo.victor.yaperecetas.model.Recipe
import com.bo.victor.yaperecetas.ui.theme.YapeRecetasTheme
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
class RecipeNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickOnRecipe_navigatesToDetailScreen() {

        composeTestRule.setContent {
            YapeRecetasTheme {
                var selectedRecipe = remember { mutableStateOf<Recipe?>(null) }
                RecipeListScreen(
                    recipes = listOf(
                        Recipe(1, "Pizza", "https://png.pngtree.com/element_our/20240724/30e6ab59f056317a22d88afb8baa8a7d.png", "Deliciosa pizza casera", listOf("Harina", "Tomate"), Origin(40.0, -3.0)),
                        Recipe(2, "Hamburguesa", "https://png.pngtree.com/element_our/20240724/30e6ab59f056317a22d88afb8baa8a7d.png", "Hamburguesa estilo Burger King", listOf("Carne Molida", "Pan", "Tomate", "Mayonesa", "Cebolla"), Origin(44.0, -17.23)),
                    ),
                    onRecipeClick = { recipe -> selectedRecipe.value = recipe }
                )
                selectedRecipe.value?.let { recipe ->
                    DetailScreen(recipe, {}, onNavigateToMap = {})
                }
            }
        }

        //Simulamos la receta "Pizza"
        composeTestRule.onNodeWithText("Pizza").performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onRoot().printToLog("TEST_LOG")

        //Verificamos que la pantalla de detalle muestra la información correcta
        composeTestRule.onNodeWithText("Deliciosa pizza casera").assertExists()
    }
}