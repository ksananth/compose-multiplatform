package com.whatsnew.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import kotlinx.coroutines.delay

@Composable
fun DownloadScreen(onNavigate: () -> Unit) {
    var isProcessing by remember { mutableStateOf(false) }

    if (isProcessing) {
        LaunchedEffect(Unit) {
            delay(2000)
            onNavigate()
            isProcessing = false
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Profile Screen", style = MaterialTheme.typography.h5)

        Button(
            onClick = {
                isProcessing = true
            }
        ) {
            Text("Download screen")
        }
    }
}