package com.example.homework3

import android.app.Application
import com.example.homework3.model.database.AppDatabase

class HomeWork3Application: Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}