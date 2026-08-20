package com.example.quiche.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiche.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(RecipeUiState())

    val uiState: StateFlow<RecipeUiState> =
        _uiState.asStateFlow()

    fun loadRecipes() {

        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }

        viewModelScope.launch {

            val result =
                recipeRepository.getRecipes()

            result
                .onSuccess { recipes ->

                    _uiState.update {
                        it.copy(
                            recipes = recipes,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }

                .onFailure { exception ->

                    _uiState.update {
                        it.copy(
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