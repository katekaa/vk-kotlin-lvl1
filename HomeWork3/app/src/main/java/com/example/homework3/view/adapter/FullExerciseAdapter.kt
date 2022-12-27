package com.example.homework3.view.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework3.R
import com.example.homework3.databinding.ExerciseItemBinding
import com.example.homework3.databinding.FullExerciseCardBinding
import com.example.homework3.model.data.Exercise

class FullExerciseAdapter() : RecyclerView.Adapter<FullExerciseAdapter.FullExerciseViewHolder>() {

    private var exercises = listOf<Exercise>()
    fun setData(data: List<Exercise>) {
        exercises = data
        notifyDataSetChanged()
    }

    class FullExerciseViewHolder(val binding: FullExerciseCardBinding) :
        RecyclerView.ViewHolder(binding.root) {


        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: Exercise) {
            binding.name.text = item.name
            binding.bodyPart.text = item.bodyPart
            binding.name.setTextColor(item.color)
            binding.bodyPart.setTextColor(item.color)



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullExerciseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = FullExerciseCardBinding.inflate(inflater, parent, false)
        return FullExerciseViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: FullExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise)
        with (holder.binding) {
            loadGif(gif, exercise.gifUrl)
        }
    }

    override fun getItemCount(): Int  = exercises.size

    private fun loadGif(imgView: ImageView, gifUrl: String) {
        if (gifUrl != ""){
            Glide.with(imgView.context)
                .asGif()
                .load(gifUrl)
                .placeholder(R.drawable.check)
                .error(R.drawable.cross)
                .centerCrop()
                .into(imgView)
        }
    }


}