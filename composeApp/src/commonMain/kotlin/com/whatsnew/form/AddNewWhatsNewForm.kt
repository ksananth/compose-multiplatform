package com.whatsnew.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun AddItemForm(
    item: WhatsNew,
    onAddItem: (WhatsNew) -> Unit,
    onDismiss: () -> Unit
) {
    var itemName by remember { mutableStateOf(item.name) }
    var storyTitle by remember { mutableStateOf(item.storyTitle) }
    var storyBadge by remember { mutableStateOf(item.storyBadge) }
    var pages by remember { mutableStateOf(item.pages) }

    var enChecked by remember { mutableStateOf(item.languages.contains(Language.EN)) }
    var frChecked by remember { mutableStateOf(item.languages.contains(Language.FR)) }
    var nlChecked by remember { mutableStateOf(item.languages.contains(Language.NL)) }
    var deChecked by remember { mutableStateOf(item.languages.contains(Language.DE)) }

    var frBrandChecked by remember { mutableStateOf(item.brands.contains(Brand.FR)) }
    var knBrandChecked by remember { mutableStateOf(item.brands.contains(Brand.KN)) }
    var hbBrandChecked by remember { mutableStateOf(item.brands.contains(Brand.HB)) }

    var iosChecked by remember { mutableStateOf(item.platforms.contains(Platform.IOS)) }
    var androidChecked by remember { mutableStateOf(item.platforms.contains(Platform.ANDROID)) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(16.dp)
        ) {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier.padding(16.dp).verticalScroll(scrollState),
            ) {
                Text(
                    text = "Add New WhatsNew",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    label = { Text("Story Name") },
                    textStyle = MaterialTheme.typography.body2,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = storyTitle,
                    onValueChange = { storyTitle = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    label = { Text("Story Title (multilan id)") },
                    textStyle = MaterialTheme.typography.body2,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = storyBadge,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    onValueChange = { storyBadge = it },
                    label = { Text("Story Badge (Multilan Id)") },
                    textStyle = MaterialTheme.typography.body2,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    elevation = 8.dp,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Platform", style = MaterialTheme.typography.body2)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = androidChecked,
                                    onCheckedChange = { androidChecked = it }
                                )
                                Text("Android", style = MaterialTheme.typography.caption)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = iosChecked,
                                    onCheckedChange = { iosChecked = it }
                                )
                                Text("iOS", style = MaterialTheme.typography.caption)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text("Language", style = MaterialTheme.typography.body2)
                        Row {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = enChecked,
                                    onCheckedChange = { enChecked = it }
                                )
                                Text("EN", style = MaterialTheme.typography.caption)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = frChecked,
                                    onCheckedChange = { frChecked = it }
                                )
                                Text("FR", style = MaterialTheme.typography.caption)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = nlChecked,
                                    onCheckedChange = { nlChecked = it }
                                )
                                Text("NL", style = MaterialTheme.typography.caption)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = deChecked,
                                    onCheckedChange = { deChecked = it }
                                )
                                Text("DE", style = MaterialTheme.typography.caption)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text("Brand", style = MaterialTheme.typography.body2)
                        Row {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = frBrandChecked,
                                    onCheckedChange = { frBrandChecked = it }
                                )
                                Text("For", style = MaterialTheme.typography.caption)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = knBrandChecked,
                                    onCheckedChange = { knBrandChecked = it }
                                )
                                Text("Fin", style = MaterialTheme.typography.caption)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = hbBrandChecked,
                                    onCheckedChange = { hbBrandChecked = it }
                                )
                                Text("Hell", style = MaterialTheme.typography.caption)
                            }
                        }

                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        pages = pages + Page(
                            title = "",
                            description = "",
                            zeplinSectionUrlAndroid = "",
                            zeplinSectionUrlIos = "",
                            delay = 10
                        )
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Add New Page")
                }
                Spacer(modifier = Modifier.height(8.dp))

                pages.forEachIndexed { index, page ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = 8.dp,
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            OutlinedTextField(
                                value = page.title,
                                textStyle = MaterialTheme.typography.body2,
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number
                                ),
                                onValueChange = { newTitle ->
                                    pages = pages.toMutableList().apply {
                                        this[index] = page.copy(title = newTitle)
                                    }
                                },
                                label = { Text("Title") },
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                value = page.description,
                                textStyle = MaterialTheme.typography.body2,
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number
                                ),
                                onValueChange = { newDescription ->
                                    pages = pages.toMutableList().apply {
                                        this[index] = page.copy(description = newDescription)
                                    }
                                },
                                label = { Text("Description") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                value = page.delay.toString(),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number
                                ),
                                textStyle = MaterialTheme.typography.body2,
                                onValueChange = { newDelay ->
                                    pages = pages.toMutableList().apply {
                                        this[index] =
                                            page.copy(delay = newDelay.toIntOrNull() ?: 10)
                                    }
                                },
                                label = { Text("Delay") },
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            if (androidChecked) {
                                Spacer(modifier = Modifier.height(8.dp))

                                OutlinedTextField(
                                    textStyle = MaterialTheme.typography.body2,
                                    value = page.zeplinSectionUrlAndroid,
                                    onValueChange = { newUrl ->
                                        pages = pages.toMutableList().apply {
                                            this[index] =
                                                page.copy(zeplinSectionUrlAndroid = newUrl)
                                        }
                                    },
                                    label = { Text("Zeplin Section Url(Android)") },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }

                            if (iosChecked) {
                                Spacer(modifier = Modifier.height(8.dp))

                                OutlinedTextField(
                                    value = page.zeplinSectionUrlIos,
                                    textStyle = MaterialTheme.typography.body2,
                                    onValueChange = { newUrl ->
                                        pages = pages.toMutableList().apply {
                                            this[index] = page.copy(zeplinSectionUrlIos = newUrl)
                                        }
                                    },
                                    label = { Text("Zeplin Section Url(IOS)") },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (itemName.isNotBlank()) {
                            val newItem = WhatsNew(
                                id = item.id,
                                name = itemName,
                                storyTitle = storyTitle,
                                storyBadge = storyBadge,
                                languages = listOfNotNull(
                                    if (enChecked) Language.EN else null,
                                    if (frChecked) Language.FR else null,
                                    if (nlChecked) Language.NL else null,
                                    if (deChecked) Language.DE else null
                                ),
                                brands = listOfNotNull(
                                    if (frBrandChecked) Brand.FR else null,
                                    if (knBrandChecked) Brand.KN else null,
                                    if (hbBrandChecked) Brand.HB else null
                                ),
                                platforms = listOfNotNull(
                                    if (iosChecked) Platform.IOS else null,
                                    if (androidChecked) Platform.ANDROID else null
                                ),
                                pages = pages
                            )
                            onAddItem(newItem)
                        }
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Save")
                }
            }
        }
    }
}