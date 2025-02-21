package com.bo.victor.yaperecetas.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.bo.victor.yaperecetas.model.Origin
import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

/****
 * Project: YapeRecetas
 * From: com.bo.victor.yaperecetas.ui.screens
 * Created by: Victor Sanjines Lopez on 20/2/2025
 * More info: www.victorsanjines.com.bo
 * All rights reserved 2025
 ****/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(origin: Origin, onBack: () -> Unit) {
    val context = LocalContext.current

    //Configuración del manejo de permisos de ubicación
    LaunchedEffect(Unit) {
        ActivityCompat.requestPermissions(
            (context as androidx.activity.ComponentActivity),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            1
        )
    }

    val recipeLocation = LatLng(origin.lat, origin.lon)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(recipeLocation, 12f)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Ubicación de la Receta") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = true)
            ) {
                Marker(
                    state = MarkerState(position = recipeLocation),
                    title = "Coordenadas de la Receta"
                )
            }
        }
    }
}
