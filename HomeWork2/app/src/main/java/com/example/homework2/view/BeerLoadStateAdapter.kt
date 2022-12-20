package com.example.homework2.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework2.databinding.BeerLoadStateBinding

class BeerLoadStateAdapter(private val tryAgain: () -> Unit) :
    LoadStateAdapter<BeerLoadStateAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BeerLoadStateBinding.inflate(inflater, parent, false)
        return Holder(binding,  tryAgain)
    }

    class Holder(private val binding: BeerLoadStateBinding, private val tryAgain: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

            init {
                binding.retryButton.setOnClickListener { tryAgain() }
            }

        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState is LoadState.Error
            binding.errorMsg.isVisible = loadState is LoadState.Error
        }

    }
}