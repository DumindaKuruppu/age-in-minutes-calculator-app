package com.example.ageinminutescalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var textViewSelectedDate: TextView? = null
    private var textViewAgeInMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        textViewSelectedDate = findViewById(R.id.textViewSelectedDate)
        textViewAgeInMinutes = findViewById(R.id.textViewAgeInMinutes)

        btnDatePicker.setOnClickListener(View.OnClickListener {
            clickDatePicker()
        })

    }

    private fun clickDatePicker() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->


                val selectedDate = "$selectedYear/${selectedMonth + 1}/$selectedDayOfMonth"
                textViewSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60_000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val differenceInMinutes =
                            (currentDate.time / 60_000) - selectedDateInMinutes
                        textViewAgeInMinutes?.text = differenceInMinutes.toString()
                    }
                }


            },
            year,
            month,
            day
        )

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86_400_000
        datePickerDialog.show()


    }
}