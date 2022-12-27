package com.example.homework3.view

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3.HomeWork3Application
import com.example.homework3.R
import com.example.homework3.databinding.FragmentHomeBinding
import com.example.homework3.model.data.DayInWeekCalendar
import com.example.homework3.model.data.Exercise
import com.example.homework3.model.data.Training
import com.example.homework3.model.data.TrainingEvent
import com.example.homework3.view.adapter.ExerciseAdapter
import com.example.homework3.view.adapter.TrainingAdapter
import com.example.homework3.view.adapter.WeekAdapter
import com.example.homework3.viewmodel.TrainingsViewModel
import com.example.homework3.viewmodel.TrainingsViewModelFactory
import java.time.LocalDate

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var weekRecycler: RecyclerView
    private lateinit var trainingRecycler: RecyclerView
    private lateinit var trainingsList: List<Training>
    private val viewModel: TrainingsViewModel by activityViewModels {
        TrainingsViewModelFactory((activity?.application as HomeWork3Application).database.trainingEventDao())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private val today = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewModel.getTrainings()
        viewModel.getExercises()
//        viewModel.getAllTrainingEvents()
        initWidgets()
//        setTrainings()
        setWeekRecycler()
        setTrainingRecycler()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setWeekRecycler() {
        val adapter = WeekAdapter()
        var icon = 0
        val daysInWeek = CalendarUtils.getDaysInWeek(CalendarUtils.selectedDate)
        Log.d("test", "daysInWeek $daysInWeek")
        val daysInWeekCalendar = mutableListOf<DayInWeekCalendar>()
        viewModel.getAllTrainingEvents().observe(viewLifecycleOwner) { list ->
            Log.d("test", "list: $list")
            val mapped = list.map { it.toTrainingEvent() }
            for (i in daysInWeek) {
                if (mapped.any { it.date == i } && i != null) {
                    icon = if (i < today) R.drawable.check else R.drawable.watch
                }
                val day = i?.let { DayInWeekCalendar(it, icon, it == today) }
                if (day != null) {
                    daysInWeekCalendar.add(day)
                }
                icon = 0
            }
            Log.d("test", "daysInWeekCalendar $daysInWeekCalendar")
//            adapter.setData(daysInWeekCalendar)
            adapter.setData(daysInWeekCalendar.subList(1, 8))
        }
        weekRecycler.adapter = adapter
    }

    private fun initWidgets() {
        weekRecycler = binding.weekRV
        trainingRecycler = binding.trainingRV
    }

//    private fun setTrainings() {
//
//        trainingsMap= Trainings.zip(colors).toMap()
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setTrainingRecycler() {
        val adapter = TrainingAdapter(requireContext())
        viewModel.trainings.observe(viewLifecycleOwner) { list ->
            adapter.setData(list)
            trainingsList = list
        }

        trainingRecycler.adapter = adapter
        trainingRecycler.itemAnimator = null
//        viewModel.exercises.observe(viewLifecycleOwner) {
//            for (t in viewModel.trainings.value!!) {
//                t.adapter.setData(it.filter { it.target == t.name })
//            }
//        }

//        val trainingsList = mutableListOf<Training>()
//        for (i in trainingsMap) {
//            val training = Training(i.key, i.value, ExerciseAdapter())
//
//            trainingsList.add(training)
//        }
//
//
//        var selectedEx: List<Exercise>
//
//        viewModel.exercises.observe(viewLifecycleOwner) { list ->
//            for (t in trainingsList) {
//                Log.d("test", "t: $t")
//                Log.d("test", "list: ${list.size}")
//                selectedEx = list.filter { it.type == t.name }
//                Log.d("test", "selected: ${selectedEx.size}")
//                selectedEx.map { it.color = t.color }
//                Log.d("test", "map2: $selectedEx")
//                t.adapter.setData(selectedEx)
//            }
//        }
//        viewModel.getExercises()

    }

    companion object {
        val COLORS = listOf(
            Color.parseColor("#41E5C9"),
            Color.parseColor("#F27D0A"),
            Color.parseColor("#F4E569"),
            Color.parseColor("#F96043")
        )

//        ContextCompat.getColor(requireContext(), R.color.turquoise),
//    ContextCompat.getColor(requireContext(),R.color.yellow),
//    ContextCompat.getColor(requireContext(),R.color.orange),
//    ContextCompat.getColor(requireContext(),R.color.red)
//)
//        val Trainings = listOf("strength", "cardio", "powerlifting", "stretching")
    }

}