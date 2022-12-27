package com.example.homework3.view

import android.os.Build
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.homework3.databinding.ActivityNewTrainingEventBinding
import com.example.homework3.model.data.TrainingEvent
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_KEYBOARD
import com.google.android.material.timepicker.TimeFormat

class NewTrainingEventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewTrainingEventBinding
    private lateinit var titleTV: EditText
    private lateinit var pickTime: Button
    private lateinit var cancel: ImageButton
    private lateinit var done: ImageButton

    private var pickedHour = 12
    private var pickedMinute = 0
    private var title = ""


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTrainingEventBinding.inflate(layoutInflater)
        initWidgets()
        setTimePicker()
        setDone()
        setCancel()
        setEditor()
        setContentView(binding.root)
    }

    private fun initWidgets() {
        titleTV = binding.titleTV
        pickTime = binding.pickTime
        done = binding.doneBT
        cancel = binding.cancelBT
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setDone() {
        done.setOnClickListener {
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setCancel() {
        finish()
    }

    private fun setEditor() {
        titleTV.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    title = titleTV.text.toString()
                    true
                }
                else -> false
            }
        }
    }


    private fun setTimePicker() {
        pickTime.setOnClickListener {

        }
        val materialTimePicker = MaterialTimePicker.Builder()
            // set the title for the alert dialog
            .setTitleText("Select Training time")
            // set the default hour for the
            // dialog when the dialog opens
            .setHour(12)
            .setInputMode(INPUT_MODE_KEYBOARD)
            // set the default minute for the
            // dialog when the dialog opens
            .setMinute(0)
            // set the time format
            // according to the region
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .build()

        materialTimePicker.addOnCancelListener {

        }

        materialTimePicker.addOnPositiveButtonClickListener {

            pickedHour = materialTimePicker.hour
            pickedMinute = materialTimePicker.minute

            // check for single digit hour hour and minute
            // and update TextView accordingly
//                val formattedTime: String = when {
//                    pickedHour > 12 -> {
//                        if (pickedMinute < 10) {
//                            "${materialTimePicker.hour - 12}:0${materialTimePicker.minute} pm"
//                        } else {
//                            "${materialTimePicker.hour - 12}:${materialTimePicker.minute} pm"
//                        }
//                    }
//                    pickedHour == 12 -> {
//                        if (pickedMinute < 10) {
//                            "${materialTimePicker.hour}:0${materialTimePicker.minute} pm"
//                        } else {
//                            "${materialTimePicker.hour}:${materialTimePicker.minute} pm"
//                        }
//                    }
//                    pickedHour == 0 -> {
//                        if (pickedMinute < 10) {
//                            "${materialTimePicker.hour + 12}:0${materialTimePicker.minute} am"
//                        } else {
//                            "${materialTimePicker.hour + 12}:${materialTimePicker.minute} am"
//                        }
//                    }
//                    else -> {
//                        if (pickedMinute < 10) {
//                            "${materialTimePicker.hour}:0${materialTimePicker.minute} am"
//                        } else {
//                            "${materialTimePicker.hour}:${materialTimePicker.minute} am"
//                        }
//                    }
//                }
        }
    }


}