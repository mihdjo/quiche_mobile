package com.example.quiche.data.model

data class AuthResponse(
    val token: String,
    val idKorisnik: Int,
    val username: String
)