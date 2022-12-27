package com.example.homework3.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.homework3.model.data.Exercise
import com.example.homework3.model.data.Training
import com.example.homework3.model.data.TrainingEvent
import com.example.homework3.model.data.TrainingEventEntity
import com.example.homework3.model.database.TrainingEventDao
import com.example.homework3.model.network.ApiService
import com.example.homework3.view.HomeFragment
import com.example.homework3.view.adapter.ExerciseAdapter
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class TrainingsViewModel(private val trainingEventDao: TrainingEventDao) : ViewModel() {

    private val _exercises = MutableLiveData<List<Exercise>>()
    val exercises: LiveData<List<Exercise>> = _exercises

    private val _trainings = MutableLiveData<List<Training>>()
    val trainings: LiveData<List<Training>> = _trainings

    //    private val _trainingsEvents = MutableLiveData<List<TrainingEvent>?>()
    val trainingEvents: LiveData<List<TrainingEventEntity>> = trainingEventDao.getAll()

//    init {
//        Log.d("test", "pure dao: ${trainingEventDao.getAll().value?.size}")
//        _trainingEvents.value = trainingEventDao.getAll().value?.map { it.toTrainingEvent() }
//    }


    fun getExercises() {
        viewModelScope.launch {

            val result = ApiService.create().getExercisesList()


            _exercises.value = result
            setExercises(result)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllTrainingEvents(): LiveData<List<TrainingEventEntity>> {
        Log.d("test", "in get events")
//        var result = listOf<TrainingEvent>()
        val fakeList = listOf(
            TrainingEvent("yoga", LocalDate.now(), 12, 20),
            TrainingEvent("cycling", LocalDate.now(), 19, 30)
        )
        return trainingEvents
//            try {
//                Log.d("test", "pure dao: ${trainingEventDao.getAll().value?.size}")
//                val result = trainingEventDao.getAll().value?.map { it.toTrainingEvent() }
//                Log.d("test", "inside vm from db: ${result?.size}")
//
//                 _trainingsEvents.value = result
//            } catch (e: Exception) {
//                Log.d("test", "exep $e")
//
//                _trainingsEvents.value = fakeList
//            }

    }

    fun getTrainings() {
        viewModelScope.launch {
            val result = ApiService.create().getTrainingsList()
            val mappedResult = result.map { mapToTraining(it) }
            _trainings.value = mappedResult

        }
    }

    private fun mapToTraining(name: String): Training {
        return Training(name, HomeFragment.COLORS.random(), ExerciseAdapter())
    }

    private fun mapColor(ex: Exercise, color: Int): Exercise {
        ex.color = color
        return ex
    }

    private fun setExercises(exercises: List<Exercise>) {
        for (t in trainings.value!!) {
            val mapped = exercises.map { mapColor(it, t.color) }
            _exercises.value = mapped
            val filtered = mapped.filter { it.target == t.name }
//            val filtered: List<Exercise> = exercises.filter { it.target == t.name }
//            val mapped = filtered.map { mapColor(it, t.color) }
            t.adapter.setData(filtered)
        }
    }

}


class TrainingsViewModelFactory(private val trainingEventDao: TrainingEventDao) :
    ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrainingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TrainingsViewModel(trainingEventDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}