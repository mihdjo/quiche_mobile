package com.example.quiche.navigation

object Routes {

    const val LOGIN = "login"

    const val HOME = "home"
    const val SAVED = "saved"
    const val CART = "cart"
    const val PROFILE = "profile"

    const val RECIPE_DETAILS = "recipe/{recipeId}"

    fun recipeDetails(recipeId: Int): String {
        return "recipe/$recipeId"
    }
}