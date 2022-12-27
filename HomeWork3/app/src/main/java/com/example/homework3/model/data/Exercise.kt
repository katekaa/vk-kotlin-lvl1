package com.example.homework3.model.data

data class Exercise(
    val name: String,
    val target: String,
    val bodyPart: String,
    val equipment: String,
    val gifUrl: String,
    var color: Int = 0
)
