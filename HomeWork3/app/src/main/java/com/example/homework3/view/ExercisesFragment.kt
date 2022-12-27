package com.example.homework3.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3.HomeWork3Application
import com.example.homework3.R
import com.example.homework3.databinding.FragmentExercisesBinding
import com.example.homework3.databinding.FragmentHomeBinding
import com.example.homework3.model.data.DayInWeekCalendar
import com.example.homework3.model.data.Training
import com.example.homework3.view.adapter.FullExerciseAdapter
import com.example.homework3.view.adapter.TrainingAdapter
import com.example.homework3.view.adapter.WeekAdapter
import com.example.homework3.viewmodel.TrainingsViewModel
import com.example.homework3.viewmodel.TrainingsViewModelFactory


class ExercisesFragment : Fragment() {

private lateinit var binding: FragmentExercisesBinding
private lateinit var recycler: RecyclerView
private val viewModel: TrainingsViewModel by activityViewModels {
        TrainingsViewModelFactory((activity?.application as HomeWork3Application).database.trainingEventDao())
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExercisesBinding.inflate(layoutInflater)
        viewModel.getTrainings()
        viewModel.getExercises()
        setRecycler()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setRecycler() {
        recycler = binding.recycler
        val adapter = FullExerciseAdapter()
        viewModel.trainings.observe(viewLifecycleOwner) { list ->
//            adapter.setData(list)
//            trainingsList = list
        }
        viewModel.exercises.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        recycler.layoutManager = GridLayoutManager(context, 2)
        recycler.adapter = adapter
    }


}