package com.whatsnew.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import lightBlack


const val imageUrl =
    "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2e/Tracy_E_Caldwell_portrait.jpg/1920px-Tracy_E_Caldwell_portrait.jpg"

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
            .padding(16.dp)
    ) {
        FloatingActionButton(
            onClick = { onNavigate() },
            text = "Next",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .zIndex(1f)
        )
    }
}


@Composable
fun ImageGallery() {
    val stories = listOf("Story 1", "Story 2", "Story 3")
    val platforms = listOf("Android", "iOS")
    val brands = listOf("FOR", "FIN", "HELL")
    val languages = listOf("EN", "FR", "NL", "DE")

    var selectedPlatform by remember { mutableStateOf(platforms[0]) }
    var selectedStory by remember { mutableStateOf(stories[0]) }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {

        TabRow(
            selectedTabIndex = stories.indexOf(selectedStory),
            backgroundColor = Color.Transparent,
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            stories.forEachIndexed { _, story ->
                Tab(
                    selected = selectedStory == story,
                    onClick = { selectedStory = story },
                    text = { Text(story) }
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(lightBlack)
                .fillMaxWidth()
        ) {
            Text(
                text = "Name: Test",
                color = Color.White,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp)
            )
            Text(
                text = "Story Title: 34566",
                color = Color.White,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(horizontal = 16.dp, 4.dp)
            )
            Text(
                text = "Story Badge: 46466",
                color = Color.White,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(horizontal = 16.dp, 4.dp)
            )

            Text(
                text = "Languages: EN, FR",
                color = Color.White,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(horizontal = 16.dp, 4.dp)
            )

            Text(
                text = "Brands: FR,GD, DF",
                color = Color.White,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(horizontal = 16.dp, 4.dp)
            )

            Text(
                text = "Platforms: Android, iOS",
                color = Color.White,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 16.dp)
            )
        }


        Text(
            "Page 1",
            modifier = Modifier.background(Color.Gray).padding(12.dp),
            style = MaterialTheme.typography.caption
        )
        Divider(modifier = Modifier.fillMaxWidth(), color = Color.Gray)
        Column(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(lightBlack)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                TabRow(
                    selectedTabIndex = platforms.indexOf(selectedPlatform),
                    backgroundColor = Color.Transparent,
                    modifier = Modifier
                        .width(200.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                ) {
                    platforms.forEachIndexed { _, platform ->
                        Tab(
                            selected = selectedPlatform == platform,
                            onClick = { selectedPlatform = platform },
                            text = { Text(platform) }
                        )
                    }
                }
            }

            brands.forEachIndexed { _, item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .padding(8.dp)
                            .width(150.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center
                    )

                    languages.forEach {
                        Row {
                            ImageCard(language = it)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun ImageCard(language: String) {

    Card(
        modifier = Modifier.padding(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                modifier = Modifier.size(150.dp),
                model = imageUrl,
                contentDescription = "Brand Image"
            )

            Text(
                text = language,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.caption
            )
        }
    }
}


