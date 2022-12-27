package com.example.homework3.view.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3.databinding.TrainingItemBinding
import com.example.homework3.model.data.Training

class TrainingAdapter(
    private val context: Context
) : RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder>() {

    private var trainings = listOf<Training>()
    fun setData(data: List<Training>) {
        trainings = data
        notifyDataSetChanged()
    }

    class TrainingViewHolder(
        private val binding: TrainingItemBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        val parentView = binding.parentView

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: Training) {
            binding.name.text = item.name
            binding.time.text = item.time
            binding.name.setTextColor(item.color)
            binding.time.setTextColor(item.color)
            binding.parentView.strokeColor = item.color

            val expanded = item.expanded

            binding.exercisesList.layoutManager = GridLayoutManager(context, 3)
            binding.exercisesList.adapter = item.adapter

            binding.subItem.visibility = if (expanded) View.VISIBLE else View.GONE

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = TrainingItemBinding.inflate(inflater, parent, false)
        return TrainingViewHolder(view, context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        val training = trainings[position]
        holder.bind(training)
        holder.parentView.setOnClickListener { v ->
            val expanded = training.expanded
            training.expanded = !expanded
            notifyItemChanged(position)
        }


    }

    override fun getItemCount(): Int = trainings.size

}