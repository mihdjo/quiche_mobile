package com.example.quiche.data.model

data class Recipe(
    val idRecept: Int,
    val naziv: String,
    val opis: String,
    val napomena: String?,
    val datumKreiranja: String,
    val idTipKuhinje: Int,
    val idKorisnik: Int
)