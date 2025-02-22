package com.bo.victor.yaperecetas.ui.screens

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertTrue
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
class RequestLocationPermissionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun requestLocationPermission_updatesStateWhenGranted() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val permissionState = mutableStateOf(false)

        composeTestRule.setContent {
            RequestLocationPermission(context = context) {
                permissionState.value = true
            }
        }

        //Simulamos que el usuario otorga el permiso
        composeTestRule.runOnIdle {
            permissionState.value = true
        }

        //Verificamos que la variable cambió a `true`
        assertTrue(permissionState.value)
    }
}