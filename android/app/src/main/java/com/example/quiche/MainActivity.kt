package com.example.quiche

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import com.example.quiche.data.local.TokenManager
import com.example.quiche.data.remote.RecipeApi
import com.example.quiche.data.remote.RetrofitClient
import com.example.quiche.data.repository.AuthRepository
import com.example.quiche.data.repository.RecipeRepository
import com.example.quiche.ui.screens.HomeScreen
import com.example.quiche.ui.screens.LoginScreen
import com.example.quiche.ui.theme.QuicheTheme
import com.example.quiche.ui.viewmodel.LoginViewModel
import com.example.quiche.ui.viewmodel.LoginViewModelFactory
import com.example.quiche.ui.viewmodel.RecipeViewModel
import com.example.quiche.ui.viewmodel.RecipeViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var recipeViewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        /*
         * Token storage
         */
        val tokenManager =
            TokenManager(applicationContext)

        /*
         * Authentication
         */
        val authRepository =
            AuthRepository(
                RetrofitClient.authApi,
                tokenManager
            )

        val loginFactory =
            LoginViewModelFactory(
                authRepository
            )

        loginViewModel =
            ViewModelProvider(
                this,
                loginFactory
            )[LoginViewModel::class.java]

        /*
         * Protected API client
         */
        val authenticatedRetrofit =
            RetrofitClient
                .createAuthenticatedRetrofit(
                    tokenManager
                )

        val recipeApi =
            authenticatedRetrofit.create(
                RecipeApi::class.java
            )

        val recipeRepository =
            RecipeRepository(recipeApi)

        val recipeFactory =
            RecipeViewModelFactory(
                recipeRepository
            )

        recipeViewModel =
            ViewModelProvider(
                this,
                recipeFactory
            )[RecipeViewModel::class.java]

        /*
         * UI
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

                if (loginUiState.isLoggedIn) {

                    LaunchedEffect(Unit) {
                        recipeViewModel.loadRecipes()
                    }

                    HomeScreen(
                        uiState = recipeUiState
                    )

                } else {

                    LoginScreen(
                        uiState = loginUiState,
                        onUsernameChange =
                            loginViewModel::onUsernameChange,
                        onPasswordChange =
                            loginViewModel::onPasswordChange,
                        onLoginClick =
                            loginViewModel::login
                    )
                }
            }
        }
    }
}