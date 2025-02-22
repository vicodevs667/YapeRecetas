package com.bo.victor.yaperecetas.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bo.victor.yaperecetas.MainActivity
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
class RecipeListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun searchRecipe_showsFilteredResult() {
        composeTestRule.setContent {
            YapeRecetasTheme {
                RecipeListScreen(
                    recipes = listOf(
                        Recipe(1, "Pizza", "https://png.pngtree.com/element_our/20240724/30e6ab59f056317a22d88afb8baa8a7d.png", "Deliciosa pizza casera", listOf("Harina", "Tomate"), Origin(40.0, -3.0)),
                        Recipe(2, "Hamburguesa", "https://png.pngtree.com/element_our/20240724/30e6ab59f056317a22d88afb8baa8a7d.png", "Hamburguesa estilo Burger King", listOf("Carne Molida", "Pan", "Tomate", "Mayonesa", "Cebolla"), Origin(44.0, -17.23)),
                    ),
                    onRecipeClick = {}
                )
            }
        }

        //Simulamos que el usuario escribe "Pizza" en el buscador
        composeTestRule.onNodeWithText("Buscar receta").performTextInput("Pizza")

        //Verificamos que la lista solo muestra "Pizza"
        composeTestRule.onAllNodesWithText("Pizza")[1].assertIsDisplayed()

        //Aseguramos que este elemento desapareció de la lista
        composeTestRule.onNodeWithText("Hamburguesa").assertDoesNotExist()
    }
}