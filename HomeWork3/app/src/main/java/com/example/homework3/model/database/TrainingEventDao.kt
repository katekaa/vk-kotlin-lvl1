package com.example.homework3.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.homework3.model.data.TrainingEventEntity

@Dao
interface TrainingEventDao {
    @Query("SELECT * FROM TrainingEvent")
    fun getAll(): LiveData<List<TrainingEventEntity>>
}