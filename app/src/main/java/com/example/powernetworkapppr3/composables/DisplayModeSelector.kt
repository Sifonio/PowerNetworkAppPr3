package com.example.powernetworkapppr3.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.powernetworkapppr3.DisplayFor

@Composable
fun DisplayModeSelector(selected: DisplayFor, onSelectedChange: (DisplayFor) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(12.dp).padding(top = 24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Практична 3: Мережа API", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                enabled = mode != selected
            ) {
                Text(when (mode) {
                    DisplayFor.BASIC_LEVEL -> "Repository & DataSource (Базовий)"
                    DisplayFor.MIDDLE_LEVEL -> "REST API & Помилки (Середній)"
                    DisplayFor.ADVANCED_LEVEL -> "Авторизація JWT & Retry (Просунутий)"
                })
            }
        }
    }
}