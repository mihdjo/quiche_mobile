package com.example.quiche.data.remote

import com.example.quiche.data.model.Recipe
import com.example.quiche.data.model.RecipeDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApi {

    @GET("api/recipes")
    suspend fun getRecipes(): Response<List<Recipe>>

    @GET("api/recipes/{id}")
    suspend fun getRecipeDetails(
        @Path("id") id: Int
    ): Response<RecipeDetails>
}