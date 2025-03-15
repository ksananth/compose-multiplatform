package com.whatsnew

import AccentColor
import BackgroundColor
import ButtonTextColor
import HomeScreen
import PrimaryTextColor
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import green
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import primary
import red
import white

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}

enum class Step {
    HOME, FORM, PREVIEW, DOWNLOAD, DONE
}

@Composable
fun App1() {
    var currentScreen by remember { mutableStateOf(Step.HOME) }
    val totalSteps = Step.entries.size // Total number of steps
    var progress by remember { mutableStateOf(0f) } // Progress state (0f to 1f)

    // Animate the progress value
    val animatedProgress by animateFloatAsState(
        targetValue = progress, // Target progress value
        animationSpec = tween(durationMillis = 500) // Animation duration (500ms)
    )

    // Update progress when the step changes
    LaunchedEffect(currentScreen) {
        progress = (currentScreen.ordinal + 1) / totalSteps.toFloat()
    }


    MaterialTheme(
        colors = lightColors(
            primary = AccentColor,
            primaryVariant = AccentColor,
            secondary = AccentColor,
            background = BackgroundColor,
            surface = BackgroundColor,
            onPrimary = ButtonTextColor,
            onSecondary = ButtonTextColor,
            onBackground = PrimaryTextColor,
            onSurface = PrimaryTextColor
        )
    ) {
        Scaffold(
            topBar = {
                Column {
                    TopAppBar(
                        title = { Text("What's New: Image Download", color = white) },
                        backgroundColor = primary,
                        navigationIcon = if (currentScreen != Step.HOME) {
                            {
                                IconButton(onClick = {
                                    currentScreen = when (currentScreen) {
                                        Step.FORM -> Step.HOME
                                        Step.PREVIEW -> Step.FORM
                                        Step.DOWNLOAD -> Step.PREVIEW
                                        else -> Step.HOME
                                    }
                                }) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back",
                                        tint = white
                                    )
                                }
                            }
                        } else null
                    )

                    LinearProgressIndicator(
                        progress = animatedProgress,
                        modifier = Modifier.fillMaxWidth(),
                        color = green
                    )

                }
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                HomeScreen(
                    currentRightContent = currentScreen,
                    onNavigate = { newContent ->
                        currentScreen = newContent
                    }
                )
            }
        }
    }
}





