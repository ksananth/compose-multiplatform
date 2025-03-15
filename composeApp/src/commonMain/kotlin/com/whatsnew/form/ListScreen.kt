package com.whatsnew.form

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun DynamicFormScreen(viewModel: AddItemViewModel = koinInject(),onNavigate: () -> Unit) {
    val items by viewModel.items.collectAsState()

    var showForm by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(items) { _, item ->
                ListItemView(item = item)
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = 4.dp,
                    onClick = { showForm = true }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                }
            }
        }


        if (showForm) {
            AddItemForm(
                onAddItem = { newItem ->
                    viewModel.addItem(newItem)
                    showForm = false
                },
                onDismiss = { showForm = false }
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
            text = "Next",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .zIndex(1f)
        )
    }
}

@Composable
fun ListItemView(item: ListItem) {
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
                    text = "Zeplin Section Name: ${item.zeplinSectionUrlAndroid}",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Title: ${item.title}",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Description: ${item.description}",
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
            }


            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd) // Align to the top-right corner
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = MaterialTheme.colors.primary
                    )
                }

                IconButton(
                    onClick = {}
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