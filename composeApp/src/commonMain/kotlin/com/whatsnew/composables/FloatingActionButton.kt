package com.whatsnew.composables

import AccentColor
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FloatingActionButton(
    onClick: () -> Unit, // Click handler
    modifier: Modifier = Modifier,
    text: String
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .size(width = 120.dp, height = 56.dp), // Set the size of the button
        shape = RoundedCornerShape(8.dp), // Rectangle with rounded corners
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AccentColor, // Use your app's accent color
            contentColor = Color.White // Text color
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 8.dp, // Default elevation
            pressedElevation = 12.dp, // Elevation when pressed
            disabledElevation = 0.dp // Elevation when disabled
        ),
        contentPadding = PaddingValues(16.dp) // Padding inside the button
    ) {
        Text(
            text = text, // Display the text
            color = Color.White, // Text color
            fontSize = 16.sp // Text size
        )
    }
}