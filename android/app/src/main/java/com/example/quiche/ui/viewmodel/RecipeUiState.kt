package com.example.quiche.ui.viewmodel

import com.example.quiche.data.model.Recipe

data class RecipeUiState(
    val recipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)