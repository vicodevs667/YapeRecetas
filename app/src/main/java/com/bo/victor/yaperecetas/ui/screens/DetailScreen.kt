package com.bo.victor.yaperecetas.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.bo.victor.yaperecetas.model.Origin
import com.bo.victor.yaperecetas.model.Recipe

/****
 * Project: YapeRecetas
 * From: com.bo.victor.yaperecetas.ui.screens
 * Created by: Victor Sanjines Lopez on 20/2/2025
 * More info: www.victorsanjines.com.bo
 * All rights reserved 2025
 ****/
@Composable
fun DetailScreen(recipe: Recipe, onNavigateToMap: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = rememberAsyncImagePainter(recipe.image),
                contentDescription = "Imagen de la receta ${recipe.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = recipe.name,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = recipe.description,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ingredientes",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        items(recipe.ingredients.size) { index ->
            IngredientItem(recipe.ingredients[index])
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onNavigateToMap(recipe.id) },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ver en el mapa")
            }
        }
    }
}

@Composable
fun IngredientItem(ingredient: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = ingredient,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        recipe = Recipe(
            id = 1,
            name = "Pizza Margherita",
            image = "https://png.pngtree.com/element_our/20240724/30e6ab59f056317a22d88afb8baa8a7d.png",
            description = "Una deliciosa pizza italiana con tomate, mozzarella y albahaca.",
            ingredients = listOf("Harina", "Tomate", "Queso mozzarella", "Albahaca"),
            origin = Origin(40.8518, 14.2681)
        ),
        onNavigateToMap = {}
    )
}
