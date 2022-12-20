package com.example.homework2.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework2.R
import com.example.homework2.databinding.BeerItemBinding

class BeerAdapter : PagingDataAdapter<Beer, BeerAdapter.Holder>(BeerDiffCallback()) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val beer = getItem(position) ?: return
        with(holder.binding) {
            loadImg(imgView, beer.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BeerItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    private fun loadImg(imgView: ImageView, imgUrl: String) {
        if (imgUrl != "") {
            Glide
                .with(imgView.context)
                .load(imgUrl)
                .error(R.drawable.error)
                .into(imgView)
        }
    }

    class Holder(val binding: BeerItemBinding) : RecyclerView.ViewHolder(binding.root)
}