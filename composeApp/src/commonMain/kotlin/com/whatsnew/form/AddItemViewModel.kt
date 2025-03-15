package com.whatsnew.form

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class AddItemViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<WhatsNew>>(emptyList())
    val items: StateFlow<List<WhatsNew>> = _items.asStateFlow()

    private val _showForm = MutableStateFlow<WhatsNew?>(null)
    val showForm: StateFlow<WhatsNew?> = _showForm.asStateFlow()

    fun submitClicked(newItem: WhatsNew) {
        val updatedList = if (_items.value.any { it.id == newItem.id }) {
            _items.value.map { if (it.id == newItem.id) newItem else it }
        } else {
            _items.value + newItem
        }
        _items.value = updatedList
        _showForm.value = null
    }

    fun onEditClicked(id: Int) {
        val item: WhatsNew? = _items.value.find { it.id == id }
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
        _showForm.value = WhatsNew.empty()
    }
}