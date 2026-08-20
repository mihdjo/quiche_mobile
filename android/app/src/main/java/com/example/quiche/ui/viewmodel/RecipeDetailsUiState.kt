package com.example.quiche.ui.viewmodel

import com.example.quiche.data.model.RecipeDetails

data class RecipeDetailsUiState(
    val recipe: RecipeDetails? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)