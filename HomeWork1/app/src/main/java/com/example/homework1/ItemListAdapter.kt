package com.example.homework1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework1.databinding.ListItemViewBinding

class ItemListAdapter:
   RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {
    class ItemViewHolder(var binding: ListItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        val textView = binding.textView
        fun bind (item: Int) {
            binding.textView.text = item.toString()
            //Log.d("doggo", "${item.toString()}")
            if (item % 2 == 0 ) binding.wrapper.setBackgroundResource(R.drawable.layout_border_even)
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
//        if (position + 1 == itemCount || (position + 2 == itemCount && itemCount %2 ==0)) {
//            // It is the last item of the list
//
//            // Set bottom margin to 72dp
//           setBottomMargin(holder.itemView, (64 * Resources.getSystem().displayMetrics.density).toInt());
//        } else {
//            // Reset bottom margin back to zero
//            setBottomMargin(holder.itemView, 0);
//        }
       holder.bind(position)
        //Log.d("doggo", "on bind VH ${position.toString()}")
       // holder.binding.textView.text = position.toString()

    }

    override fun getItemCount(): Int {
       return list.size
    }

    private fun setBottomMargin(view: View, bottomMargin: Int) {
        if (view.layoutParams is ViewGroup.MarginLayoutParams) {
            var params =view.layoutParams
            (params as ViewGroup.MarginLayoutParams).setMargins(params.leftMargin, params.topMargin, params.rightMargin, bottomMargin)
            view.requestLayout()
        }
    }


}