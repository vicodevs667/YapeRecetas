package com.bo.victor.yaperecetas.ui.viewmodel

import com.bo.victor.yaperecetas.data.remote.ApiService
import com.bo.victor.yaperecetas.data.repository.RecipeRepository
import com.bo.victor.yaperecetas.model.Origin
import com.bo.victor.yaperecetas.model.Recipe
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/****
 * Project: YapeRecetas
 * From: com.bo.victor.yaperecetas.ui.viewmodel
 * Created by: Victor Sanjines Lopez on 20/2/2025
 * More info: www.victorsanjines.com.bo
 * All rights reserved 2025
 ****/
//@ExperimentalCoroutinesApi
@OptIn(ExperimentalCoroutinesApi::class)
class RecipeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var repository: RecipeRepository

    private lateinit var viewModel: RecipeViewModel
    private lateinit var closeable: AutoCloseable

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mock(RecipeRepository::class.java)
        viewModel = RecipeViewModel(repository)
        closeable = MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Restablecer el dispatcher después de cada test
        closeable.close()
    }


    @Test
    fun `fetchRecipes updates recipes list`() = runTest {
        val mockApiService = mock(ApiService::class.java)
        val repository = RecipeRepository(mockApiService)

        val mockRecipes = listOf(
            Recipe(1, "Pizza", "https://example.com/pizza.jpg", "Pizza deliciosa", listOf("Harina", "Tomate"), Origin(40.0, -3.0))
        )

        doReturn(mockRecipes).`when`(mockApiService).getRecipes() //Mockear la llamada de la API

        val viewModel = RecipeViewModel(repository) //Instanciar el ViewModel con el repo corregido
        viewModel.fetchRecipes()
        advanceUntilIdle() // Esperar ejecución de coroutines

        assertEquals(1, viewModel.recipes.first().size)
        assertEquals("Pizza", viewModel.recipes.value[0].name)
    }
}