package com.example.powernetworkapppr3.auth

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

val Context.authDataStore by preferencesDataStore(name = "auth_prefs")

class AuthDataStore(context: Context) {
    private val dataStore = context.authDataStore
    val tokenFlow: Flow<String?> = dataStore.data.map { it[stringPreferencesKey("token")] }
    suspend fun saveToken(token: String) = dataStore.edit { it[stringPreferencesKey("token")] = token }
    suspend fun clearToken() = dataStore.edit { it.remove(stringPreferencesKey("token")) }
}

class AuthViewModel(private val authDataStore: AuthDataStore) : ViewModel() {
    fun mockLogin(user: String, pass: String, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            if(user == "admin" && pass == "1234") {
                authDataStore.saveToken("mock_jwt_token_dsf345sd")
                onSuccess()
            } else {
                onError()
            }
        }
    }
    fun logout() = viewModelScope.launch { authDataStore.clearToken() }
}