package com.whatsnew.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlin.random.Random

@Composable
fun AddItemForm(
    onAddItem: (ListItem) -> Unit, // Callback to add a new item
    onDismiss: () -> Unit // Callback to dismiss the form
) {
    // State for input fields
    var itemName by remember { mutableStateOf("") }
    var zeplinSectionName by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    // State for checkboxes
    var enChecked by remember { mutableStateOf(false) }
    var frChecked by remember { mutableStateOf(false) }
    var nlChecked by remember { mutableStateOf(false) }
    var deChecked by remember { mutableStateOf(false) }

    var frBrandChecked by remember { mutableStateOf(false) }
    var knBrandChecked by remember { mutableStateOf(false) }
    var hbBrandChecked by remember { mutableStateOf(false) }

    var iosChecked by remember { mutableStateOf(false) }
    var androidChecked by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Add New WhatsNew",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Input field for the item name
                OutlinedTextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = zeplinSectionName,
                    onValueChange = { zeplinSectionName = it },
                    label = { Text("Zeplin Section Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Language checkboxes
                Text("Language", style = MaterialTheme.typography.subtitle1)
                Row {
                    Row {
                        Checkbox(
                            checked = enChecked,
                            onCheckedChange = { enChecked = it }
                        )
                        Text("EN", style = MaterialTheme.typography.h6)
                    }
                    Row {
                        Checkbox(
                            checked = frChecked,
                            onCheckedChange = { frChecked = it }
                        )
                        Text("FR", style = MaterialTheme.typography.h6)
                    }
                    Row {
                        Checkbox(
                            checked = nlChecked,
                            onCheckedChange = { nlChecked = it }
                        )
                        Text("NL", style = MaterialTheme.typography.h6)
                    }

                    Row {
                        Checkbox(
                            checked = deChecked,
                            onCheckedChange = { deChecked = it }
                        )
                        Text("DE", style = MaterialTheme.typography.h6)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Brand checkboxes

                Text("Brand", style = MaterialTheme.typography.subtitle1)
                Row {
                    Row {
                        Checkbox(
                            checked = frBrandChecked,
                            onCheckedChange = { frBrandChecked = it }
                        )
                        Text("Fortis", style = MaterialTheme.typography.h6)
                    }
                    Row {
                        Checkbox(
                            checked = knBrandChecked,
                            onCheckedChange = { knBrandChecked = it }
                        )
                        Text("Fintro", style = MaterialTheme.typography.h6)
                    }
                    Row {
                        Checkbox(
                            checked = hbBrandChecked,
                            onCheckedChange = { hbBrandChecked = it }
                        )
                        Text("Hellobank", style = MaterialTheme.typography.h6)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Platform checkboxes
                Text("Platform", style = MaterialTheme.typography.subtitle1)
                Row {
                    Row {
                        Checkbox(
                            checked = iosChecked,
                            onCheckedChange = { iosChecked = it }
                        )
                        Text("Android", style = MaterialTheme.typography.h6)
                    }
                    Row {
                        Checkbox(
                            checked = androidChecked,
                            onCheckedChange = { androidChecked = it }
                        )
                        Text("iOS", style = MaterialTheme.typography.h6)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Add button
                Button(
                    onClick = {
                        if (itemName.isNotBlank()) {
                            val newItem = ListItem(
                                id = Random.nextInt(),
                                name = itemName,
                                zeplinSectionName = zeplinSectionName,
                                title = title,
                                description = description,
                                languages = listOfNotNull(
                                    if (enChecked) "en" else null,
                                    if (frChecked) "fr" else null,
                                    if (nlChecked) "nl" else null,
                                    if (deChecked) "de" else null
                                ),
                                brands = listOfNotNull(
                                    if (frBrandChecked) "FR" else null,
                                    if (knBrandChecked) "KN" else null,
                                    if (hbBrandChecked) "HB" else null
                                ),
                                platforms = listOfNotNull(
                                    if (iosChecked) "IOS" else null,
                                    if (androidChecked) "ANDROID" else null
                                )
                            )
                            onAddItem(newItem) // Add the new item
                        }
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Add")
                }
            }
        }
    }
}