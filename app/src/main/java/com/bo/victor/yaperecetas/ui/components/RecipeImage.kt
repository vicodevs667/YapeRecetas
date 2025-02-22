package com.bo.victor.yaperecetas.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bo.victor.yaperecetas.R

/****
 * Project: YapeRecetas
 * From: com.bo.victor.yaperecetas.ui.components
 * Created by: Victor Sanjines Lopez on 21/2/2025
 * More info: www.victorsanjines.com.bo
 * All rights reserved 2025
 ****/
@Composable
fun RecipeImage(
        imageUrl: String,
        contentDescription: String,
        modifier: Modifier = Modifier
    ) {
        AsyncImage(
            model = imageUrl.ifEmpty { "https://yaperecetas.placeholder.com/default.png" },
            contentDescription = contentDescription,
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp)),
            contentScale = ContentScale.Fit,
            error = painterResource(R.drawable.placeholder_image)
        )
    }