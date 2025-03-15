package com.whatsnew.composables


import AccentColor
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay


@Composable
fun ReleaseScreen(onNavigate: () -> Unit) {
    var isProcessing by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) } // Loading state

    if (isProcessing) {
        LaunchedEffect(Unit) {
            delay(3000)
            isLoading = false // Hide loading indicator

            onNavigate()
            isProcessing = false
        }
    }



    Column(modifier = Modifier.padding(90.dp), verticalArrangement =Arrangement.Center,) {
        var text by remember { mutableStateOf("") }

        Text("Release Name:")
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter Release Name") },
            placeholder = { Text("Eg: 2025M05") },
            modifier = Modifier.padding(top = 8.dp).fillMaxWidth()
        )
    }

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)), // Semi-transparent overlay
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.padding(16.dp),
                color = AccentColor
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) // Add padding to avoid overlapping with the screen edges
    ) {
        FloatingActionButton(
            onClick = { onNavigate() }, // Navigate to the next screen
            text = "Next", // Customize the button text
            modifier = Modifier
                .align(Alignment.BottomEnd) // Position the FAB in the bottom-right corner
                .zIndex(1f) // Ensure the FAB is on top of other content
        )
    }
}