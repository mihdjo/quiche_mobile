package com.example.quiche.data.repository

import com.example.quiche.data.local.TokenManager
import com.example.quiche.data.model.AuthResponse
import com.example.quiche.data.model.LoginRequest
import com.example.quiche.data.remote.AuthApi

class AuthRepository(
    private val authApi: AuthApi,
    private val tokenManager: TokenManager
) {

    suspend fun login(
        username: String,
        password: String
    ): Result<AuthResponse> {

        return try {

            val response = authApi.login(
                LoginRequest(
                    username = username,
                    password = password
                )
            )

            if (response.isSuccessful) {

                val authResponse = response.body()

                if (authResponse != null) {

                    tokenManager.saveToken(authResponse.token)

                    Result.success(authResponse)

                } else {
                    Result.failure(
                        Exception("Server nije vratio podatke.")
                    )
                }

            } else {

                Result.failure(
                    Exception(
                        when (response.code()) {
                            401 -> "Pogrešan username ili password."
                            else -> "Greška servera: ${response.code()}"
                        }
                    )
                )
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}