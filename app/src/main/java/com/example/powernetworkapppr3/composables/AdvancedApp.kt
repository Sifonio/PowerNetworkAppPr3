package com.example.powernetworkapppr3.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.powernetworkapppr3.auth.*

@Composable
fun AdvancedApp() {
    val context = LocalContext.current
    val authDataStore = remember { AuthDataStore(context) }
    val viewModel = remember { AuthViewModel(authDataStore) }

    val savedToken by authDataStore.tokenFlow.collectAsState(initial = null)

    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (savedToken == null) {
            Text("Авторизація в системі ЛЕП", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = user, onValueChange = { user = it }, label = { Text("Логін (admin)") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = pass, onValueChange = { pass = it }, label = { Text("Пароль (1234)") }, modifier = Modifier.fillMaxWidth())

            errorMsg?.let { Text(it, color = MaterialTheme.colorScheme.error) }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.mockLogin(user, pass, onSuccess = { errorMsg = null }, onError = { errorMsg = "Невірний логін або пароль" })
            }, modifier = Modifier.fillMaxWidth()) { Text("Увійти") }
        } else {
            Text("Ви успішно авторизовані!", style = MaterialTheme.typography.titleLarge)
            Text("Ваш токен доступу: $savedToken", color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { viewModel.logout() }) { Text("Вийти з системи") }
        }
    }
}