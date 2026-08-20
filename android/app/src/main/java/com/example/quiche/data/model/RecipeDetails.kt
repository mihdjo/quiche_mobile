package com.example.quiche.data.model

data class RecipeDetails(
    val idRecept: Int,
    val naziv: String,
    val opis: String,
    val napomena: String?,
    val datumKreiranja: String,

    val idTipKuhinje: Int,
    val tipKuhinje: String,

    val idKorisnik: Int,
    val autorUsername: String,

    val sastojci: List<RecipeIngredient>
)