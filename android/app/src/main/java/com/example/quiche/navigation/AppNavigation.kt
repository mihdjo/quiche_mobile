package com.example.quiche.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quiche.ui.screens.HomeScreen
import com.example.quiche.ui.screens.LoginScreen
import com.example.quiche.ui.screens.PlaceholderScreen
import com.example.quiche.ui.screens.RecipeDetailsScreen
import com.example.quiche.ui.viewmodel.LoginUiState
import com.example.quiche.ui.viewmodel.RecipeDetailsUiState
import com.example.quiche.ui.viewmodel.RecipeUiState

@Composable
fun QuicheApp(
    initiallyLoggedIn: Boolean,

    loginUiState: LoginUiState,
    recipeUiState: RecipeUiState,
    recipeDetailsUiState: RecipeDetailsUiState,

    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,

    onLoadRecipes: () -> Unit,
    onLoadRecipeDetails: (Int) -> Unit
) {

    val navController = rememberNavController()

    val startDestination = remember {
        if (initiallyLoggedIn) {
            Routes.HOME
        } else {
            Routes.LOGIN
        }
    }

    LaunchedEffect(loginUiState.isLoggedIn) {

        if (loginUiState.isLoggedIn) {

            navController.navigate(Routes.HOME) {

                popUpTo(Routes.LOGIN) {
                    inclusive = true
                }

                launchSingleTop = true
            }
        }
    }

    val navBackStackEntry by
    navController.currentBackStackEntryAsState()

    val currentRoute =
        navBackStackEntry
            ?.destination
            ?.route

    val bottomRoutes = setOf(
        Routes.HOME,
        Routes.SAVED,
        Routes.CART,
        Routes.PROFILE
    )

    val showBottomBar =
        currentRoute in bottomRoutes

    Scaffold(
        bottomBar = {

            if (showBottomBar) {

                QuicheBottomBar(
                    currentRoute = currentRoute,

                    onNavigate = { route ->

                        navController.navigate(route) {

                            /*
                             * HOME koristimo kao osnovu
                             * bottom-navigation back stack-a.
                             */
                            popUpTo(Routes.HOME) {
                                saveState = true
                            }

                            /*
                             * Sprečava pravljenje više kopija
                             * istog ekrana u back stack-u.
                             */
                            launchSingleTop = true

                            /*
                             * Vraća prethodno stanje ekrana
                             * ako se korisnik vrati na njega.
                             */
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {

            /*
             * ========================================================
             * LOGIN
             * ========================================================
             */
            composable(Routes.LOGIN) {

                LoginScreen(
                    uiState = loginUiState,
                    onUsernameChange = onUsernameChange,
                    onPasswordChange = onPasswordChange,
                    onLoginClick = onLoginClick
                )
            }

            /*
             * ========================================================
             * HOME / RECIPES
             * ========================================================
             */
            composable(Routes.HOME) {

                /*
                 * Kada HOME ekran uđe u composition,
                 * učitavamo recepte sa servera.
                 */
                LaunchedEffect(Unit) {
                    onLoadRecipes()
                }

                HomeScreen(
                    uiState = recipeUiState,

                    onRecipeClick = { recipeId ->

                        navController.navigate(
                            Routes.recipeDetails(recipeId)
                        )
                    }
                )
            }

            /*
             * ========================================================
             * SAVED RECIPES
             * ========================================================
             *
             * Za sada placeholder.
             * Sledeće ćemo ovde povezati pravi API.
             */
            composable(Routes.SAVED) {

                PlaceholderScreen(
                    title = "Saved Recipes"
                )
            }

            /*
             * ========================================================
             * CART
             * ========================================================
             */
            composable(Routes.CART) {

                PlaceholderScreen(
                    title = "Cart"
                )
            }

            /*
             * ========================================================
             * PROFILE
             * ========================================================
             */
            composable(Routes.PROFILE) {

                PlaceholderScreen(
                    title = "Profile"
                )
            }

            /*
             * ========================================================
             * RECIPE DETAILS
             * ========================================================
             *
             * Ruta izgleda:
             *
             * recipe/1
             * recipe/2
             * ...
             */
            composable(
                route = Routes.RECIPE_DETAILS,

                arguments = listOf(
                    navArgument("recipeId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->

                val recipeId =
                    backStackEntry
                        .arguments
                        ?.getInt("recipeId")
                        ?: return@composable

                /*
                 * Kada se recipeId promeni,
                 * učitavamo detalje novog recepta.
                 *
                 * Primer:
                 *
                 * Carbonara → recipeId = 1
                 * Omlet     → recipeId = 2
                 */
                LaunchedEffect(recipeId) {
                    onLoadRecipeDetails(recipeId)
                }

                RecipeDetailsScreen(
                    uiState = recipeDetailsUiState,

                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}


/*
 * ================================================================
 * BOTTOM NAVIGATION
 * ================================================================
 */

@Composable
private fun QuicheBottomBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {

    val destinations = listOf(

        BottomDestination(
            route = Routes.HOME,
            label = "Recipes",
            marker = "R"
        ),

        BottomDestination(
            route = Routes.SAVED,
            label = "Saved",
            marker = "S"
        ),

        BottomDestination(
            route = Routes.CART,
            label = "Cart",
            marker = "C"
        ),

        BottomDestination(
            route = Routes.PROFILE,
            label = "Profile",
            marker = "P"
        )
    )

    NavigationBar {

        destinations.forEach { destination ->

            NavigationBarItem(

                selected =
                    currentRoute == destination.route,

                onClick = {
                    onNavigate(destination.route)
                },

                icon = {
                    Text(destination.marker)
                },

                label = {
                    Text(destination.label)
                }
            )
        }
    }
}

private data class BottomDestination(
    val route: String,
    val label: String,
    val marker: String
)