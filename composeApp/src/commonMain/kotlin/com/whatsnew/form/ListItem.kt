package com.whatsnew.form

data class ListItem(
    val id: Int,
    val name: String,
    val zeplinSectionName: String,
    val title: String,
    val description: String,
    val languages: List<String>,
    val brands: List<String>,
    val platforms: List<String>
)