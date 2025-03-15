package com.whatsnew.form

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class AddItemViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<ListItem>>(emptyList())
    val items: StateFlow<List<ListItem>> = _items.asStateFlow()

    private val _showForm = MutableStateFlow<ListItem?>(null)
    val showForm: StateFlow<ListItem?> = _showForm.asStateFlow()

    fun addItem(newItem: ListItem) {
        val updatedList = _items.value + newItem
        _items.value = updatedList
        _showForm.value = null
    }

    fun onEditClicked(id: Int) {
        val item: ListItem? = _items.value.find { it.id == id }
        item?.let {
            _showForm.value = it
        }
    }

    fun onDeleteClicked(id: Int) {
        _items.value = _items.value.filter { it.id != id }
    }

    fun onDismissDialog() {
        _showForm.value = null
    }

    fun addNewWhatsNew() {
        _showForm.value = ListItem.empty()
    }
}