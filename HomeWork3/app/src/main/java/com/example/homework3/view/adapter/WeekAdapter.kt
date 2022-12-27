package com.example.homework3.view.adapter

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3.databinding.CalendarCellBinding
import com.example.homework3.databinding.DayItemBinding
import com.example.homework3.model.data.DayInWeekCalendar
import com.example.homework3.model.data.TrainingEvent
import com.example.homework3.view.CalendarUtils
import java.time.LocalDate

class WeekAdapter(
) : RecyclerView.Adapter<WeekAdapter.WeekViewHolder>() {

    private var daysInWeek = listOf<DayInWeekCalendar>()
    fun setData(data: List<DayInWeekCalendar>) {
        daysInWeek = data
        notifyDataSetChanged()
    }


    class WeekViewHolder(
        private val binding: DayItemBinding,
        private val daysInWeek: List<DayInWeekCalendar?>
    ) : RecyclerView.ViewHolder(binding.root) {
        val day = binding.day
        val dayOfWeek = binding.dayOfWeek
        val icon = binding.icon
        val parentView = binding.parentView
        val linear = binding.linear


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DayItemBinding.inflate(inflater, parent, false)
        return WeekViewHolder(view, daysInWeek)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: WeekViewHolder, position: Int) {
        val day = daysInWeek[position]
        if (day == null) {
            holder.day.text = ""
            holder.dayOfWeek.text = ""
        } else {
            holder.day.text = day.date.dayOfMonth.toString()
            holder.dayOfWeek.text = CalendarUtils.getMonthFromDate(day.date).subSequence(0, 2)
            holder.icon.isVisible = day.image != 0
            if (day.image != 0) {
                day.image?.let { holder.icon.setImageResource(it) }
            }

            if (day.today) {
                holder.parentView.setBackgroundColor(Color.LTGRAY)
                holder.linear.setBackgroundColor(Color.LTGRAY)
                holder.day.setTextColor(Color.BLACK)
                holder.dayOfWeek.setTextColor(Color.BLACK)
            } else {
                holder.parentView.setBackgroundColor(Color.BLACK)
                holder.linear.setBackgroundColor(Color.BLACK)
                holder.day.setTextColor(Color.WHITE)
                holder.dayOfWeek.setTextColor(Color.WHITE)
            }
        }

    }

    override fun getItemCount(): Int = daysInWeek.size

}