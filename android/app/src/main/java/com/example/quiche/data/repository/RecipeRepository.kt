package com.example.quiche.data.repository

import com.example.quiche.data.model.Recipe
import com.example.quiche.data.remote.RecipeApi
import com.example.quiche.data.model.RecipeDetails

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

    suspend fun getRecipeDetails(
        id: Int
    ): Result<RecipeDetails> {

        return try {

            val response =
                recipeApi.getRecipeDetails(id)

            if (response.isSuccessful) {

                val recipe = response.body()

                if (recipe != null) {
                    Result.success(recipe)
                } else {
                    Result.failure(
                        Exception(
                            "Server nije vratio detalje recepta."
                        )
                    )
                }

            } else {

                Result.failure(
                    Exception(
                        when (response.code()) {
                            401 ->
                                "Sesija nije validna. Prijavite se ponovo."

                            404 ->
                                "Recept nije pronađen."

                            else ->
                                "Greška servera: ${response.code()}"
                        }
                    )
                )
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}