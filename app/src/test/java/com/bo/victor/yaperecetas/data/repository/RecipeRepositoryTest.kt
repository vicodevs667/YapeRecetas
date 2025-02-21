package com.bo.victor.yaperecetas.data.repository

import com.bo.victor.yaperecetas.data.remote.ApiService
import com.bo.victor.yaperecetas.model.Origin
import com.bo.victor.yaperecetas.model.Recipe
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

/****
 * Project: YapeRecetas
 * From: com.bo.victor.yaperecetas.data.repository
 * Created by: Victor Sanjines Lopez on 20/2/2025
 * More info: www.victorsanjines.com.bo
 * All rights reserved 2025
 ****/
class RecipeRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var repository: RecipeRepository

    @Before
    fun setUp() {
        apiService = mock(ApiService::class.java)
        repository = RecipeRepository(apiService)
    }

    @Test
    fun `getRecipes returns list of recipes`() = runBlocking {
        //Simulamos una respuesta de la API
        val mockRecipes = listOf(
            Recipe(1, "Pizza", "https://png.pngtree.com/element_our/20240724/30e6ab59f056317a22d88afb8baa8a7d.png", "Deliciosa pizza casera", listOf("Harina", "Tomate"), Origin(40.0, -3.0)),
            Recipe(2, "Hamburguesa", "https://png.pngtree.com/element_our/20240724/30e6ab59f056317a22d88afb8baa8a7d.png", "Hamburguesa estilo Burger King", listOf("Carne Molida", "Pan", "Tomate", "Mayonesa", "Cebolla"), Origin(44.0, -17.23)),
        )

        `when`(apiService.getRecipes()).thenReturn(mockRecipes)
        val result = repository.getRecipes()

        assertEquals(2, result.size)
        assertEquals("Pizza", result[0].name)
        assertEquals("Hamburguesa", result[1].name)
    }
}