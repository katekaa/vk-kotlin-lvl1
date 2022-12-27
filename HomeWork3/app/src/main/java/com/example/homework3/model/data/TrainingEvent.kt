package com.example.homework3.model.data

import java.time.LocalDate

data class TrainingEvent(
    val name: String,
    val date: LocalDate,
    val hour: Int,
    val minute: Int
)
