package com.example.homework3.model.database

import android.content.Context
import androidx.room.*
import com.example.homework3.model.data.TrainingEventEntity

@Database(entities = arrayOf(TrainingEventEntity::class), version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trainingEventDao(): TrainingEventDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "MyDatabase"
                )
                    .createFromAsset("database/database3.db")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}