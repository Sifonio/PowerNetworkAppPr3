package com.example.powernetworkapppr3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.powernetworkapppr3.composables.*
import com.example.powernetworkapppr3.ui.theme.PowerNetworkAppPr3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PowerNetworkAppPr3Theme {
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        DisplayModeSelector(selected = displayFor, onSelectedChange = { displayFor = it })
                        Spacer(modifier = Modifier.height(8.dp))
                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicApp()       // Repository + Data Source
                            DisplayFor.MIDDLE_LEVEL -> MediumApp()     // REST API + Помилки
                            DisplayFor.ADVANCED_LEVEL -> AdvancedApp() // JWT Auth + Retry
                        }
                    }
                }
            }
        }
    }
}