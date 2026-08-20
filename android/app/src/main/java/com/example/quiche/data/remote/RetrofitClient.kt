package com.example.quiche.data.remote

import com.example.quiche.data.local.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL =
        "http://10.0.2.2:8080/"

    /*
     * Public Retrofit client.
     *
     * Koristi se za:
     * - login
     * - register
     */
    private val publicRetrofit: Retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    val authApi: AuthApi by lazy {
        publicRetrofit.create(AuthApi::class.java)
    }

    /*
     * Retrofit client za zaštićene rute.
     *
     * JwtInterceptor automatski dodaje:
     *
     * Authorization: Bearer <token>
     */
    fun createAuthenticatedRetrofit(
        tokenManager: TokenManager
    ): Retrofit {

        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(
                JwtInterceptor(tokenManager)
            )
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }
}