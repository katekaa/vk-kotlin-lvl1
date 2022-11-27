package com.example.homework2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homework2.model.repository.BeerRepository

@Suppress("UNCHECKED_CAST")
    class ViewModelFactory(private val repo: BeerRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repo) as T
        }
    }
