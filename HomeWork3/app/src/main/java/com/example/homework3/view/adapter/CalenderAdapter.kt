package com.example.homework3.view.adapter

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3.databinding.CalendarCellBinding
import com.example.homework3.view.CalendarUtils
import java.time.LocalDate

class CalenderAdapter(
    private val daysInMonth: List<LocalDate?>,
    private val onItemListener: OnItemListener
) : RecyclerView.Adapter<CalenderAdapter.CalendarViewHolder>() {

    class CalendarViewHolder(
        private val binding: CalendarCellBinding,
        private val onItemListener: OnItemListener,
        private val daysInMonth: List<LocalDate?>
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val parentView = binding.parentView
        val dayTV = binding.dayTV

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemListener.onItemClick(adapterPosition, daysInMonth[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CalendarCellBinding.inflate(inflater, parent, false)
        val layoutParams = view.root.layoutParams
        layoutParams.height = (parent.height * 0.166666666).toInt()
        return CalendarViewHolder(view, onItemListener, daysInMonth)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val date = daysInMonth[position]
        if (date == null) {
            holder.dayTV.text = ""
        }
//        holder.bind()
        else {
            holder.dayTV.text = date.dayOfMonth.toString()
            if (date == CalendarUtils.selectedDate) {
                holder.parentView.setBackgroundColor(Color.LTGRAY)
            }
        }

    }

    override fun getItemCount(): Int = daysInMonth.size

    interface OnItemListener {
        fun onItemClick(position: Int, date: LocalDate?)
    }
}