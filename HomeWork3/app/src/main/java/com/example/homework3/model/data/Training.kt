package com.example.homework3.model.data

import com.example.homework3.view.adapter.ExerciseAdapter

class Training(
    val name: String,
    val color: Int,
    val adapter: ExerciseAdapter,
    val time: String = "45 min",
    var expanded: Boolean = false
)