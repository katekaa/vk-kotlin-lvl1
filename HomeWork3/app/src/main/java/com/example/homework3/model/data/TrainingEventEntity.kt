package com.example.homework3.model.data

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*

@Entity(tableName = "TrainingEvent")
data class TrainingEventEntity(
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "Date")
    val date: String,

    @ColumnInfo(name = "Hour")
    val hour: Int,

    @ColumnInfo(name = "Minute")
    val minute: Int
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun toTrainingEvent(): TrainingEvent {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        val date1: Date =
            if (dateFormat.parse(date) != null) dateFormat.parse(date) else Calendar.getInstance().time
        Log.d("test", "date1: $date1")
        return TrainingEvent(
            name = name,
            date = date1.toInstant()?.atZone(ZoneId.systemDefault())!!.toLocalDate(),
            hour = hour,
            minute = minute
        )
    }
}


