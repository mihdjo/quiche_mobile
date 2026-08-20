package com.example.quiche.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quiche.data.model.Recipe
import com.example.quiche.ui.viewmodel.RecipeUiState

@Composable
fun HomeScreen(
    uiState: RecipeUiState,
    onRecipeClick: (Int) -> Unit
) {

    when {

        uiState.isLoading -> {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        uiState.errorMessage != null -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = uiState.errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        else -> {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                item {

                    Text(
                        text = "Recipes",
                        style =
                            MaterialTheme.typography.headlineLarge,
                        modifier =
                            Modifier.padding(bottom = 8.dp)
                    )
                }

                items(
                    items = uiState.recipes,
                    key = { recipe ->
                        recipe.idRecept
                    }
                ) { recipe ->

                    RecipeCard(
                        recipe = recipe,
                        onClick = {
                            onRecipeClick(recipe.idRecept)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun RecipeCard(
    recipe: Recipe,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = recipe.naziv,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = recipe.opis,
                style = MaterialTheme.typography.bodyMedium
            )

            recipe.napomena?.let { napomena ->

                Text(
                    text = napomena,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}