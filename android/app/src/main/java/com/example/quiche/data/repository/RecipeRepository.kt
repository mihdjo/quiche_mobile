package com.example.quiche.data.repository

import com.example.quiche.data.model.Recipe
import com.example.quiche.data.remote.RecipeApi

class RecipeRepository(
    private val recipeApi: RecipeApi
) {

    suspend fun getRecipes(): Result<List<Recipe>> {

        return try {

            val response = recipeApi.getRecipes()

            if (response.isSuccessful) {

                val recipes = response.body()

                if (recipes != null) {
                    Result.success(recipes)
                } else {
                    Result.failure(
                        Exception("Server nije vratio recepte.")
                    )
                }

            } else {

                Result.failure(
                    Exception(
                        when (response.code()) {
                            401 -> "Sesija nije validna. Prijavite se ponovo."
                            else -> "Greška servera: ${response.code()}"
                        }
                    )
                )
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}