package com.example.homework3.view.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3.databinding.TrainingEventCellBinding
import com.example.homework3.model.data.TrainingEvent

class TrainingEventAdapter() :
    RecyclerView.Adapter<TrainingEventAdapter.TrainingEventViewHolder>() {

    private var trainingEvents = listOf<TrainingEvent>()
    fun setData(data: List<TrainingEvent>) {
        trainingEvents = data
        notifyDataSetChanged()
    }

    class TrainingEventViewHolder(private val binding: TrainingEventCellBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val nameTV = binding.nameTV
        private val timeTV = binding.timeTV

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: TrainingEvent) {
            nameTV.text = item.name
            val h_str = if (item.hour < 10) "0${item.hour}" else item.hour.toString()
            val m_str = if (item.minute < 10) "0${item.minute}" else item.minute.toString()
            timeTV.text = "$h_str:$m_str"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingEventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = TrainingEventCellBinding.inflate(inflater, parent, false)
        return TrainingEventViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TrainingEventViewHolder, position: Int) {
        val event = trainingEvents[position]

        holder.bind(event)


    }

    override fun getItemCount(): Int = trainingEvents.size

}