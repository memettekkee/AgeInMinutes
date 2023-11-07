package com.dicoding.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)

        }

    }
    fun clickDatePicker(view: View){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedyear, selectedmonth, selecteddayOfMonth ->
            Toast.makeText(this, "it works", Toast.LENGTH_LONG).show()

            val selectedDate = "$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"

            val tvSelectedDate: TextView = findViewById(R.id.tvSelectedDate)
            tvSelectedDate.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)

            val selectedDateInMinutes = theDate!!.time / 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateInMinutes = currentDate!!.time / 60000

            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

            val tvSelectedDateInMinutes: TextView = findViewById(R.id.tvSelectedDateInMinute)
            tvSelectedDateInMinutes.setText(differenceInMinutes.toString())

        },year, month, day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}