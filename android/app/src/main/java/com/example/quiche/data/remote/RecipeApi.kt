package com.example.quiche.data.remote

import com.example.quiche.data.model.Recipe
import retrofit2.Response
import retrofit2.http.GET

interface RecipeApi {

    @GET("api/recipes")
    suspend fun getRecipes(): Response<List<Recipe>>
}