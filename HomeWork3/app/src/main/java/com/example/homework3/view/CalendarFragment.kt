package com.example.homework3.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3.HomeWork3Application
import com.example.homework3.databinding.FragmentCalendarBinding
import com.example.homework3.model.data.TrainingEvent
import com.example.homework3.view.CalendarUtils.Companion.getDaysInMonth
import com.example.homework3.view.CalendarUtils.Companion.getMonthFromDate
import com.example.homework3.view.CalendarUtils.Companion.selectedDate
import com.example.homework3.view.adapter.CalenderAdapter
import com.example.homework3.view.adapter.TrainingEventAdapter
import com.example.homework3.viewmodel.TrainingsViewModel
import com.example.homework3.viewmodel.TrainingsViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
class CalendarFragment : Fragment(), CalenderAdapter.OnItemListener {
    private lateinit var binding: FragmentCalendarBinding
    private lateinit var month: TextView
    private lateinit var calendarRecycler: RecyclerView
    private lateinit var eventsRecycler: RecyclerView
    private lateinit var time: LocalTime
    private lateinit var newEvent: FloatingActionButton

    private val viewModel: TrainingsViewModel by activityViewModels {
        TrainingsViewModelFactory((activity?.application as HomeWork3Application).database.trainingEventDao())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(layoutInflater)
//        viewModel.getTrainingEvents()
        initWidgets()
        time = LocalTime.now()
        setMonthView()
        setTrainingEventAdapter()

        return binding.root
    }


    private fun setMonthView() {
        month.text = getMonthFromDate(selectedDate)
        val daysInMonth = getDaysInMonth(selectedDate)

        val adapter = CalenderAdapter(daysInMonth, this)
        calendarRecycler.layoutManager = GridLayoutManager(requireContext(), 7)
        calendarRecycler.adapter = adapter
    }


    private fun initWidgets() {
        calendarRecycler = binding.calendarRV
        eventsRecycler = binding.eventsRV
        month = binding.monthTV
        newEvent = binding.newEvent
        binding.newEvent.setOnClickListener { openNewTrainingEvent() }
        binding.prevMonth.setOnClickListener { previousMonthAction() }
        binding.nextMonth.setOnClickListener { nextMonthAction() }
    }

    private fun previousMonthAction() {
        selectedDate = selectedDate.minusMonths(1)
        setMonthView()
    }

    private fun nextMonthAction() {
        selectedDate = selectedDate.plusMonths(1)
        setMonthView()
    }

    private fun setTrainingEventAdapter() {
        val adapter = TrainingEventAdapter()
        viewModel.getAllTrainingEvents().observe(viewLifecycleOwner) { list ->
            Log.d("test", "in calendar fr: ${list?.size}")
            val mapped = list.map { it.toTrainingEvent() }
            adapter.setData(mapped.filter { it.date == selectedDate })

        }
//        val trainingEvents = TrainingEvent.getTrainingEventsForDate(selectedDate)

        eventsRecycler.layoutManager = LinearLayoutManager(context)
        eventsRecycler.adapter = adapter
    }

    private fun openNewTrainingEvent() {
        val parentActivity = requireActivity()
        val intent = Intent(parentActivity, NewTrainingEventActivity::class.java)
        (parentActivity as StartMyActivity).startMyIntent(intent)
    }

    override fun onItemClick(position: Int, date: LocalDate?) {
        if (date != null) {
            selectedDate = date
            setMonthView()
            setTrainingEventAdapter()
        }
    }

}