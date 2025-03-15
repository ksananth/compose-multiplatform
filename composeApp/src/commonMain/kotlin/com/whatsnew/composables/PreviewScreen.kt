package com.whatsnew.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay


const val imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2e/Tracy_E_Caldwell_portrait.jpg/1920px-Tracy_E_Caldwell_portrait.jpg"

@Composable
fun PreviewScreen(onNavigate: () -> Unit) {
    var isProcessing by remember { mutableStateOf(false) }

    if (isProcessing) {
        LaunchedEffect(Unit) {
            delay(2000)
            onNavigate()
            isProcessing = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        ImageGallery()
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


@Composable
fun ImageGallery() {
    val platforms = listOf("Android", "iOS")
    val brands = listOf("FR", "KN", "HB")
    val languages = listOf("EN", "FR", "NL", "DE")

    var selectedPlatform by remember { mutableStateOf(platforms[0]) }

    Column {
        // Platform Tabs
        TabRow(selectedTabIndex = platforms.indexOf(selectedPlatform)) {
            platforms.forEachIndexed { index, platform ->
                Tab(
                    selected = selectedPlatform == platform,
                    onClick = { selectedPlatform = platform },
                    text = { Text(platform) }
                )
            }
        }

        LazyColumn {
            items(brands.size) { index: Int ->
                Text(text = brands[index], style = MaterialTheme.typography.subtitle1, modifier = Modifier.padding(8.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth().height(300.dp)
                ) {
                    items(languages) { language ->
                        ImageCard(platform = selectedPlatform, brand = brands[index], language = language)
                    }
                }
            }
        }
    }
}

@Composable
fun ImageCard(platform: String, brand: String, language: String) {

    Card(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
     /*       Image(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = "Brand Image",
                modifier = Modifier.fillMaxWidth().height(120.dp)
            )*/
            AsyncImage(
                modifier = Modifier.size(240.dp),
                model = imageUrl,
                contentDescription = "Brand Image"
            )

            Text(text = "$brand - $language", modifier = Modifier.padding(8.dp))
        }
    }
}


