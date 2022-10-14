package com.example.homework1.model

import android.content.Context
import com.example.homework1.R

class ResourcesProvider(private val applicationContext: Context) {
    val itemsList = applicationContext.resources.getIntArray(R.array.items)
}