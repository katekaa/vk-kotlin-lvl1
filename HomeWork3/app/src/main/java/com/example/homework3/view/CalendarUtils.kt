package com.example.homework3.view

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

class CalendarUtils {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        var selectedDate: LocalDate = LocalDate.now()

        @RequiresApi(Build.VERSION_CODES.O)

        fun getDaysInMonth(date: LocalDate): List<LocalDate?> {
            val daysInMonthList = mutableListOf<LocalDate?>()

            val yearMonth = YearMonth.from(date)
            val daysInMonth = yearMonth.lengthOfMonth()
            val firstDayOfMonth = selectedDate.withDayOfMonth(1)
            val dayOfWeek = firstDayOfMonth.dayOfWeek.value
            val today = LocalDate.now().dayOfMonth

            for (i in 1..42) {
                if (i < dayOfWeek || i >= daysInMonth + dayOfWeek) {
                    daysInMonthList.add(null)
                } else {
                    daysInMonthList.add(
                        LocalDate.of(
                            selectedDate.year,
                            selectedDate.month,
                            i - dayOfWeek + 1
                        )
                    )
                }
            }
            return daysInMonthList
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getDaysInWeek(date: LocalDate): List<LocalDate?> {
            val daysInWeekList = mutableListOf<LocalDate>()
            var current = sundayForDate(date)
            val endDate = current?.plusWeeks(1)
            while (current?.isBefore(endDate) == true || current?.isEqual(endDate) == true) {
                daysInWeekList.add(current)
                current = current.plusDays(1)
            }
            return daysInWeekList
        }

        @RequiresApi(Build.VERSION_CODES.O)
        private fun sundayForDate(currDay: LocalDate): LocalDate? {
            var current = currDay
            val oneWeekAgo = current.minusWeeks(1)
            while (current.isAfter(oneWeekAgo)) {
                if (current.dayOfWeek == DayOfWeek.SUNDAY)
                    return current
                current = current.minusDays(1);
            }
            return null

        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getMonthFromDate(date: LocalDate): String {
            val formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH)
            return date.format(formatter)
        }


    }
}