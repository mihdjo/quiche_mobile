package com.example.quiche.data.local

import android.content.Context

class TokenManager(context: Context) {

    private val preferences = context.getSharedPreferences(
        "quiche_preferences",
        Context.MODE_PRIVATE
    )

    companion object {
        private const val KEY_TOKEN = "jwt_token"
    }

    fun saveToken(token: String) {
        preferences
            .edit()
            .putString(KEY_TOKEN, token)
            .apply()
    }

    fun getToken(): String? {
        return preferences.getString(KEY_TOKEN, null)
    }

    fun clearToken() {
        preferences
            .edit()
            .remove(KEY_TOKEN)
            .apply()
    }

    fun hasToken(): Boolean {
        return !getToken().isNullOrBlank()
    }
}