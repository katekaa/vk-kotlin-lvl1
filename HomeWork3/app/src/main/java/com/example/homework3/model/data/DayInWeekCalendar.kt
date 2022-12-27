package com.example.homework3.model.data

import java.time.LocalDate

data class DayInWeekCalendar(
    val date: LocalDate,
    val image: Int?,
    var today: Boolean
)
