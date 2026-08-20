package com.example.quiche.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quiche.data.model.RecipeIngredient
import com.example.quiche.ui.viewmodel.RecipeDetailsUiState

@Composable
fun RecipeDetailsScreen(
    uiState: RecipeDetailsUiState,
    onBack: () -> Unit
) {

    when {

        uiState.isLoading -> {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement =
                    Arrangement.Center,
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                CircularProgressIndicator()
            }
        }

        uiState.errorMessage != null -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                verticalArrangement =
                    Arrangement.Center,
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Text(
                    text = uiState.errorMessage,
                    color =
                        MaterialTheme.colorScheme.error
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Button(
                    onClick = onBack
                ) {
                    Text("Nazad")
                }
            }
        }

        uiState.recipe != null -> {

            val recipe = uiState.recipe

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement =
                    Arrangement.spacedBy(16.dp)
            ) {

                item {

                    Button(
                        onClick = onBack
                    ) {
                        Text("Nazad")
                    }
                }

                item {

                    Text(
                        text = recipe.naziv,
                        style =
                            MaterialTheme
                                .typography
                                .headlineLarge
                    )
                }

                item {

                    Text(
                        text =
                            "${recipe.tipKuhinje} kuhinja",
                        style =
                            MaterialTheme
                                .typography
                                .titleMedium
                    )

                    Text(
                        text =
                            "Autor: ${recipe.autorUsername}",
                        style =
                            MaterialTheme
                                .typography
                                .bodyMedium
                    )
                }

                item {

                    HorizontalDivider()
                }

                item {

                    Text(
                        text = recipe.opis,
                        style =
                            MaterialTheme
                                .typography
                                .bodyLarge
                    )
                }

                item {

                    Text(
                        text = "Sastojci",
                        style =
                            MaterialTheme
                                .typography
                                .headlineSmall
                    )
                }

                items(
                    items = recipe.sastojci,
                    key = { ingredient ->
                        ingredient.idSastojak
                    }
                ) { ingredient ->

                    IngredientRow(
                        ingredient = ingredient
                    )
                }

                recipe.napomena?.let { napomena ->

                    item {

                        HorizontalDivider()

                        Text(
                            text = "Napomena",
                            style =
                                MaterialTheme
                                    .typography
                                    .headlineSmall
                        )

                        Text(
                            text = napomena,
                            style =
                                MaterialTheme
                                    .typography
                                    .bodyMedium
                        )
                    }
                }

                item {
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun IngredientRow(
    ingredient: RecipeIngredient
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                text = ingredient.naziv
            )

            Text(
                text =
                    "${formatQuantity(ingredient.kolicina)} " +
                            ingredient.jedinicaMere
            )
        }
    }
}

private fun formatQuantity(
    quantity: Double
): String {

    return if (quantity % 1.0 == 0.0) {
        quantity.toInt().toString()
    } else {
        quantity.toString()
    }
}