package com.example.quiche

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import com.example.quiche.data.local.TokenManager
import com.example.quiche.data.remote.RecipeApi
import com.example.quiche.data.remote.RetrofitClient
import com.example.quiche.data.repository.AuthRepository
import com.example.quiche.data.repository.RecipeRepository
import com.example.quiche.navigation.QuicheApp
import com.example.quiche.ui.theme.QuicheTheme
import com.example.quiche.ui.viewmodel.LoginViewModel
import com.example.quiche.ui.viewmodel.LoginViewModelFactory
import com.example.quiche.ui.viewmodel.RecipeDetailsViewModel
import com.example.quiche.ui.viewmodel.RecipeDetailsViewModelFactory
import com.example.quiche.ui.viewmodel.RecipeViewModel
import com.example.quiche.ui.viewmodel.RecipeViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var recipeDetailsViewModel: RecipeDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        /*
         * ============================================================
         * LOCAL STORAGE
         * ============================================================
         */

        val tokenManager =
            TokenManager(applicationContext)

        val initiallyLoggedIn =
            tokenManager.hasToken()

        /*
         * ============================================================
         * AUTHENTICATION
         * ============================================================
         */

        val authRepository =
            AuthRepository(
                authApi = RetrofitClient.authApi,
                tokenManager = tokenManager
            )

        val loginViewModelFactory =
            LoginViewModelFactory(
                authRepository = authRepository
            )

        loginViewModel =
            ViewModelProvider(
                this,
                loginViewModelFactory
            )[LoginViewModel::class.java]

        /*
         * ============================================================
         * AUTHENTICATED RETROFIT CLIENT
         * ============================================================
         */

        val authenticatedRetrofit =
            RetrofitClient.createAuthenticatedRetrofit(
                tokenManager = tokenManager
            )

        val recipeApi =
            authenticatedRetrofit.create(
                RecipeApi::class.java
            )

        val recipeRepository =
            RecipeRepository(
                recipeApi = recipeApi
            )

        /*
         * ============================================================
         * RECIPE FEED
         * ============================================================
         */

        val recipeViewModelFactory =
            RecipeViewModelFactory(
                recipeRepository = recipeRepository
            )

        recipeViewModel =
            ViewModelProvider(
                this,
                recipeViewModelFactory
            )[RecipeViewModel::class.java]

        /*
         * ============================================================
         * RECIPE DETAILS
         * ============================================================
         */

        val recipeDetailsViewModelFactory =
            RecipeDetailsViewModelFactory(
                recipeRepository = recipeRepository
            )

        recipeDetailsViewModel =
            ViewModelProvider(
                this,
                recipeDetailsViewModelFactory
            )[RecipeDetailsViewModel::class.java]

        /*
         * ============================================================
         * UI
         * ============================================================
         */

        setContent {

            QuicheTheme {

                val loginUiState by
                loginViewModel
                    .uiState
                    .collectAsState()

                val recipeUiState by
                recipeViewModel
                    .uiState
                    .collectAsState()

                val recipeDetailsUiState by
                recipeDetailsViewModel
                    .uiState
                    .collectAsState()

                QuicheApp(
                    initiallyLoggedIn = initiallyLoggedIn,

                    loginUiState = loginUiState,
                    recipeUiState = recipeUiState,
                    recipeDetailsUiState = recipeDetailsUiState,

                    onUsernameChange =
                        loginViewModel::onUsernameChange,

                    onPasswordChange =
                        loginViewModel::onPasswordChange,

                    onLoginClick =
                        loginViewModel::login,

                    onLoadRecipes =
                        recipeViewModel::loadRecipes,

                    onLoadRecipeDetails =
                        recipeDetailsViewModel::loadRecipe
                )
            }
        }
    }
}