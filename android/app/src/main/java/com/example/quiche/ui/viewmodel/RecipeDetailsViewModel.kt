package com.example.quiche.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiche.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            RecipeDetailsUiState()
        )

    val uiState: StateFlow<RecipeDetailsUiState> =
        _uiState.asStateFlow()

    fun loadRecipe(id: Int) {

        _uiState.update {
            it.copy(
                recipe = null,
                isLoading = true,
                errorMessage = null
            )
        }

        viewModelScope.launch {

            val result =
                recipeRepository
                    .getRecipeDetails(id)

            result
                .onSuccess { recipe ->

                    _uiState.update {
                        it.copy(
                            recipe = recipe,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }

                .onFailure { exception ->

                    _uiState.update {
                        it.copy(
                            recipe = null,
                            isLoading = false,
                            errorMessage =
                                exception.message
                                    ?: "Došlo je do greške."
                        )
                    }
                }
        }
    }
}