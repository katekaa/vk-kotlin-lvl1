package com.example.homework2.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.homework2.model.repository.BeerRepository
import com.example.homework2.view.Beer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainViewModel(private val repo: BeerRepository): ViewModel() {

    val beerFlow: Flow<PagingData<Beer>> by lazy {
        repo.getPagedBeers().map { it -> it.map { it.toBeer() } }.cachedIn(viewModelScope)
    }
}
