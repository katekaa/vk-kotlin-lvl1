package com.example.homework1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class ItemViewModel(
    private val state: SavedStateHandle
) : ViewModel() {

    private var _list: MutableLiveData<MutableList<Int>> = state.getLiveData(ITEMS_LIST)
    val list: LiveData<MutableList<Int>>
        get() = _list

    init {
        if (_list.value == null) getItems()
    }

    private fun getItems() {
        _list.value = mutableListOf()
    }

    fun addElem(elem: Int) {
        _list.value?.add(elem)
        saveCurrentList()
    }

    private fun saveCurrentList() {
        state[ITEMS_LIST] = _list.value
    }

    companion object {
        private const val ITEMS_LIST = "itemsList"
    }
}