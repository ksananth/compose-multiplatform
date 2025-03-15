package com.whatsnew.form

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DynamicFormScreen(onNavigate: () -> Unit) {
    // State for the list of items
    var items by remember { mutableStateOf<List<ListItem>>(emptyList()) }

    // State to control the visibility of the form
    var showForm by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Display the list of items
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(items) { _, item ->
                ListItemView(item = item)
            }

            // Add a special "Add" item at the end of the list
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = 4.dp,
                    onClick = { showForm = true } // Open the form
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


        // Show the form when `showForm` is true
        if (showForm) {
            AddItemForm(
                onAddItem = { newItem ->
                    items = items + newItem // Add the new item to the list
                    showForm = false // Close the form
                },
                onDismiss = { showForm = false } // Close the form
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) // Add padding to avoid overlapping with the screen edges
    ) {
        com.whatsnew.composables.FloatingActionButton(
            onClick = { onNavigate() }, // Navigate to the next screen
            text = "Next", // Customize the button text
            modifier = Modifier
                .align(Alignment.BottomEnd) // Position the FAB in the bottom-right corner
                .zIndex(1f) // Ensure the FAB is on top of other content
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
                // Display the name
                Text(
                    text = "Name: ${item.name}",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Display the Zeplin Section Name
                Text(
                    text = "Zeplin Section Name: ${item.zeplinSectionName}",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Display the Title
                Text(
                    text = "Title: ${item.title}",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Display the Description
                Text(
                    text = "Description: ${item.description}",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Display the selected Languages
                Text(
                    text = "Languages: ${item.languages.joinToString(", ")}",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Display the selected Brands
                Text(
                    text = "Brands: ${item.brands.joinToString(", ")}",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Display the selected Platforms
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
                // Edit Icon
                IconButton(
                    onClick = {} // Trigger the edit callback
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = MaterialTheme.colors.primary
                    )
                }

                // Delete Icon
                IconButton(
                    onClick = {} // Trigger the delete callback
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