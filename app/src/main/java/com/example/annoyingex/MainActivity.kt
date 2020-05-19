package com.example.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var btnHWGA : Button
    private lateinit var btnCancel : Button

    private lateinit var tvDate : TextView
    private lateinit var tvText : TextView

    private lateinit var annoyingExApp: AnnoyingExApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnHWGA = findViewById(R.id.btnHWGA)
        btnCancel = findViewById(R.id.btnCancel)
        tvDate = findViewById(R.id.tvDate)
        tvText = findViewById(R.id.tvText)
        annoyingExApp = application as AnnoyingExApp

        httpCall()

        btnHWGA.setOnClickListener {
            annoyingExApp.annoyingExManager.startFunction()
        }

        btnCancel.setOnClickListener {
            annoyingExApp.annoyingExManager.stopWork()
        }


        val message = intent.getStringExtra("text")
        val date = intent.getStringExtra("time")

        tvText.text = message
        tvDate.text = date
    }

    private fun httpCall() {
        annoyingExApp.apiManager.fetchData()
    }
}
