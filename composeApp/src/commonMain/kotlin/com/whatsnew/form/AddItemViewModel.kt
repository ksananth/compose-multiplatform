package com.whatsnew.form

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class AddItemViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<ListItem>>(emptyList())
    val items: StateFlow<List<ListItem>> = _items.asStateFlow()

    fun addItem(newItem: ListItem) {
        val updatedList = _items.value + newItem
        _items.value = updatedList
    }

    fun onEditClicked() {
        TODO("Not yet implemented")
    }

    fun onDeleteClicked(id: Int) {
        _items.value = _items.value.filter { it.id != id }
    }
}