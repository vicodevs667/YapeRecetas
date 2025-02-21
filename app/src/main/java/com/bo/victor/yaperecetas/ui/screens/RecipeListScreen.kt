package com.bo.victor.yaperecetas.ui.screens

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.bo.victor.yaperecetas.R
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
fun RecipeListScreen(recipes: List<Recipe>, onRecipeClick: (Recipe) -> Unit) {

    //Para manejar el estado del texto de la búsqueda
    var searchQuery = remember { mutableStateOf("") }

    val filteredRecipes = remember(searchQuery.value, recipes) {
        val result = recipes.filter { recipe ->
            recipe.name.lowercase().contains(searchQuery.value.lowercase()) ||
            recipe.ingredients.any { it.lowercase().contains(searchQuery.value.lowercase()) }
        }
        result
    }

    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .systemBarsPadding()
    ) {
        OutlinedTextField(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            label = { Text("Buscar receta") },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .clickable { focusRequester.requestFocus() },
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(filteredRecipes) { recipe ->
                RecipeItem(recipe = recipe, onClick = { onRecipeClick(recipe) })
            }
        }
    }
}

@Composable
fun RecipeItem(recipe: Recipe, onClick: () -> Unit) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = recipe.image.ifEmpty { "https://yaperecetas.placeholder.com/default.png" },
                contentDescription = "Imagen de la receta ${recipe.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp)),
                contentScale = ContentScale.Fit,
                error = painterResource(R.drawable.placeholder_image) //Imagen local si la URL es inválida
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeItemPreview() {
    RecipeItem(
        recipe = Recipe(
            id = 1,
            name = "Pizza Margherita",
            image = "https://png.pngtree.com/element_our/20240724/30e6ab59f056317a22d88afb8baa8a7d.png",
            description = "Una deliciosa pizza italiana con tomate, mozzarella y albahaca.",
            ingredients = listOf("Harina", "Tomate", "Queso mozzarella", "Albahaca"),
            origin = Origin(40.8518, 14.2681)
        )
    ) {}
}














