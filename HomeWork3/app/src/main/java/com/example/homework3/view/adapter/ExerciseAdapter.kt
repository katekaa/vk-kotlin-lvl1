package com.example.homework3.view.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3.databinding.ExerciseItemBinding
import com.example.homework3.model.data.Exercise

class ExerciseAdapter() : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    private var exercises = listOf<Exercise>()
    fun setData(data: List<Exercise>) {
        exercises = data
        notifyDataSetChanged()
    }

    private val limit = 9

    class ExerciseViewHolder(private val binding: ExerciseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: Exercise) {
            binding.name.text = item.name
            binding.bodyPart.text = item.bodyPart
            binding.name.setTextColor(item.color)
            binding.bodyPart.setTextColor(item.color)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ExerciseItemBinding.inflate(inflater, parent, false)
        return ExerciseViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise)
    }

    override fun getItemCount(): Int {
        return if (exercises.size > limit) limit else exercises.size
    }

}