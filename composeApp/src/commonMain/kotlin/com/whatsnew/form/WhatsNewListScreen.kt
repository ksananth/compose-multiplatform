package com.whatsnew.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun WhatsNewFormScreen(
    viewModel: AddItemViewModel = koinInject(),
    onNavigate: () -> Unit
) {
    val items by viewModel.items.collectAsState()
    val showForm by viewModel.showForm.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(items) { _, item ->
                ListItemView(item = item, viewModel = viewModel)
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = 4.dp,
                    onClick = { viewModel.addNewWhatsNew() }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Add, contentDescription = "Add new WhatsNew")
                            Text(
                                "Add new WhatsNew",
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }


        showForm?.let {
            AddItemForm(
                item = it,
                onAddItem = { newItem ->
                    viewModel.submitClicked(newItem)
                },
                onDismiss = {
                    viewModel.onDismissDialog()
                }
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        com.whatsnew.composables.FloatingActionButton(
            onClick = { onNavigate() },
            text = "Download",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .zIndex(1f)
        )
    }
}

@Composable
internal fun ListItemView(item: WhatsNew, viewModel: AddItemViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Box {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Name: ${item.name}",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Story Title: ${item.storyTitle}",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Story Badge: ${item.storyBadge}",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Languages: ${item.languages.joinToString(", ")}",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Brands: ${item.brands.joinToString(", ")}",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Platforms: ${item.platforms.joinToString(", ")}",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                item.pages.forEachIndexed { index, page ->
                    Text(
                        "Page ${index + 1}",
                        modifier = Modifier.background(Color.Gray).padding(12.dp),
                        style = MaterialTheme.typography.body1
                    )
                    Divider(modifier = Modifier.fillMaxWidth(), color = Color.Gray)

                    Text(
                        text = "Title: ${page.title}",
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Description: ${page.description}",
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Zeplin Section Name(Android): ${page.zeplinSectionUrlAndroid}",
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Zeplin Section Name(IOS): ${page.zeplinSectionUrlIos}",
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                }
            }


            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {
                        viewModel.onEditClicked(item.id)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = MaterialTheme.colors.primary
                    )
                }

                IconButton(
                    onClick = {
                        viewModel.onDeleteClicked(item.id)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colors.error
                    )
                }
            }
        }
    }
}