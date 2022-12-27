package com.example.homework3.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework3.databinding.FragmentExercisesBinding


class ExercisesFragment : Fragment() {

private lateinit var binding: FragmentExercisesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExercisesBinding.inflate(layoutInflater)
        return binding.root
    }


}