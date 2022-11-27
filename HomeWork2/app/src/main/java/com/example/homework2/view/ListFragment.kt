package com.example.homework2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework2.databinding.ListFragmentBinding
import com.example.homework2.model.DefaultBeerRepository
import com.example.homework2.model.database.AppDatabase
import com.example.homework2.model.network.ApiService
import com.example.homework2.viewModel.MainViewModel
import com.example.homework2.viewModel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ListFragment : Fragment() {

    private lateinit var recycler: RecyclerView
    private lateinit var binding: ListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListFragmentBinding.inflate(inflater)
        recycler = binding.recyclerView
        val db = AppDatabase.getDatabase(requireActivity()).beerDao()
        val repo = DefaultBeerRepository(db, ApiService.create())
        val viewModel: MainViewModel by viewModels {
            ViewModelFactory(repo)
        }

        val adapter = BeerAdapter()

        val tryAgain: TryAgain = { adapter.retry() }
        val footer = BeerLoadStateAdapter(tryAgain)
        binding.retryButton.setOnClickListener { adapter.retry() }

        val adapterWithLoadState = adapter.withLoadStateFooter(footer)
        recycler.layoutManager = GridLayoutManager(
            activity,
            2
        )
        recycler.adapter = adapterWithLoadState

        observeBeers(adapter, viewModel)
        observeLoadState(adapter)

        return binding.root
    }

    private fun observeLoadState(adapter: BeerAdapter) {
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { it ->
                binding.recyclerView.isVisible = it.source.refresh is LoadState.NotLoading
                binding.progressBar.isVisible = it.refresh is LoadState.Loading
                binding.retryButton.isVisible =
                    it.refresh is LoadState.Error && adapter.itemCount == 0
            }
        }
    }

    private fun observeBeers(adapter: BeerAdapter, viewModel: MainViewModel) {
        lifecycleScope.launch {
            viewModel.beerFlow.collectLatest { adapter.submitData(it) }
        }
    }
}