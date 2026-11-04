package com.example.powernetworkapppr3.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.powernetworkapppr3.data.*

@Composable
fun BasicApp() {
    val repository = remember { NetworkRepository(LocalNetworkDataSource()) }
    var loads by remember { mutableStateOf(repository.getAll()) }

    var node by remember { mutableStateOf("") }
    var mw by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Базовий рівень: Локальний Repository", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = node, onValueChange = { node = it }, label = { Text("Вузол") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = mw, onValueChange = { mw = it }, label = { Text("Навантаження МВт") }, modifier = Modifier.fillMaxWidth())
        Button(onClick = {
            if(node.isNotBlank() && mw.isNotBlank()) {
                repository.insert(NetworkLoad(loads.size + 1, node, mw.toDoubleOrNull() ?: 0.0))
                loads = repository.getAll()
                node = ""; mw = ""
            }
        }, modifier = Modifier.fillMaxWidth()) { Text("Додати в Repository") }

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(loads) { item ->
                Text("Вузол: ${item.nodeName} | ${item.loadMw} МВт", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}