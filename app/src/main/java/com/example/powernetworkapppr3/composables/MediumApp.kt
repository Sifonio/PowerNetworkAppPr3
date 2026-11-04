package com.example.powernetworkapppr3.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.example.powernetworkapppr3.api.RetrofitClient

@Composable
fun MediumApp() {
    var errorMsg by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Середній рівень: Retrofit та Помилки", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            scope.launch {
                isLoading = true
                errorMsg = null
                try {
                    RetrofitClient.api.getSubstations() // Спроба доступу до API
                } catch (e: Exception) {
                    errorMsg = "Помилка мережі: Не вдалося підключитися до сервера. Перевірте з'єднання."
                } finally {
                    isLoading = false
                }
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Отримати список підстанцій")
        }

        Spacer(modifier = Modifier.height(16.dp))
        if(isLoading) CircularProgressIndicator()
        errorMsg?.let {
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)) {
                Text(it, modifier = Modifier.padding(16.dp), color = MaterialTheme.colorScheme.error)
            }
        }
    }
}