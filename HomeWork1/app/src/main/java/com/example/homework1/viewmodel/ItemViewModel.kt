package com.example.homework1.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.homework1.model.ResourcesProvider

class ItemViewModel(private val resourceProvider: ResourcesProvider, private val state: SavedStateHandle): ViewModel() {

    companion object {
        private val ITEMS_LIST = "itemsList"
    }

    private var _list: MutableLiveData<MutableList<Int>> = state.getLiveData(ITEMS_LIST)
    val list: LiveData<MutableList<Int>>
        get() = _list
    init {
        if (_list.value == null) getItems()
    }
    private fun getItems() {
        _list.value = resourceProvider.itemsList.toMutableList()
    }

    fun addElem(elem: Int) {
        _list.value?.add(elem)
    }

    fun saveCurrentList(list: MutableList<Int>) {
        state[ITEMS_LIST] = list
    }

//    fun getCurrentList(): MutableList<Int> {
//        return state[ITEMS_LIST]?: mutableListOf<Int>()
//    }
}