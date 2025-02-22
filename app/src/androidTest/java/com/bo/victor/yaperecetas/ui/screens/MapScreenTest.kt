package com.bo.victor.yaperecetas.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bo.victor.yaperecetas.model.Origin
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
class MapScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun mapScreen_displaysMarkerCorrectly() {
        val testOrigin = Origin(40.0, -3.0) //Coordenadas de prueba

        composeTestRule.setContent {
            MapScreen(origin = testOrigin, onBack = {})
        }

        //Verificamos que el mapa existe en la pantalla
        composeTestRule.onNodeWithTag("MapView").assertExists()
    }
}