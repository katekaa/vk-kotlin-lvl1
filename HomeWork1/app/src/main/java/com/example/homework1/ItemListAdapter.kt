package com.example.homework1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework1.databinding.ListItemViewBinding

class ItemListAdapter :
    RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {
    class ItemViewHolder(var binding: ListItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Int) {
            binding.textView.text = item.toString()
            if (item % 2 == 0) binding.wrapper.setBackgroundResource(R.drawable.layout_border_even)
            else binding.wrapper.setBackgroundResource(R.drawable.layout_border_odd)
        }
    }

    var list: MutableList<Int> = mutableListOf()

    fun setData(data: MutableList<Int>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ListItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}