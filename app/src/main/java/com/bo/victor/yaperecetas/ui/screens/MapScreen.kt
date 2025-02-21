package com.bo.victor.yaperecetas.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.bo.victor.yaperecetas.model.Origin
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.bo.victor.yaperecetas.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
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
    val customMarkerBitmap = remember { mutableStateOf<BitmapDescriptor?>(null) }

    //Verificación de permisos
    var locationPermissionGranted = remember { mutableStateOf(false) }
    RequestLocationPermission(context = context) {
        locationPermissionGranted.value = true
    }

    if (origin == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Ubicación no disponible", style = MaterialTheme.typography.headlineMedium)
        }
        return
    }

    val recipeLocation = LatLng(origin.lat, origin.lon)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(recipeLocation, 14f)
    }

    LaunchedEffect(context) {
        customMarkerBitmap.value = BitmapDescriptorFactory.fromResource(R.drawable.marker)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Ubicación de la Receta") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = locationPermissionGranted.value)
            ) {
                Marker(
                    state = MarkerState(position = recipeLocation),
                    title = "Coordenadas de la Receta",
                    icon = customMarkerBitmap.value
                )
            }
        }
    }
}

@Composable
fun RequestLocationPermission(context: Context, onPermissionGranted: () -> Unit) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            onPermissionGranted()
        } else {
            Toast.makeText(context, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            onPermissionGranted()
        }
    }
}
