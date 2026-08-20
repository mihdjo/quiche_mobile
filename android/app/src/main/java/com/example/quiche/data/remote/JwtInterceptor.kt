package com.example.quiche.data.remote

import com.example.quiche.data.local.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

class JwtInterceptor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val token = tokenManager.getToken()

        // Ako nema tokena, šaljemo zahtev bez Authorization headera.
        if (token.isNullOrBlank()) {
            return chain.proceed(originalRequest)
        }

        val authenticatedRequest = originalRequest
            .newBuilder()
            .header(
                "Authorization",
                "Bearer $token"
            )
            .build()

        return chain.proceed(authenticatedRequest)
    }
}