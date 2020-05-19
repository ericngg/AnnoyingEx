package com.example.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var btnHWGA : Button
    private lateinit var btnBlock : Button

    private lateinit var tvDate : TextView
    private lateinit var tvText : TextView

    private lateinit var annoyingExApp: AnnoyingExApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnHWGA = findViewById(R.id.btnHWGA)
        btnBlock = findViewById(R.id.btnBlock)
        tvDate = findViewById(R.id.tvDate)
        tvText = findViewById(R.id.tvText)
        annoyingExApp = application as AnnoyingExApp

        // Fetch messages
        httpCall()

        // Here we go again onClick
        btnHWGA.setOnClickListener {
            annoyingExApp.annoyingExManager.startFunction()
        }

        // Block onClick
        btnBlock.setOnClickListener {
            annoyingExApp.annoyingExManager.stopWork()
        }


        // Updates message fields
        updateFields()
    }

    // Helper method for http request
    private fun httpCall() {
        annoyingExApp.apiManager.fetchData()
    }

    // Helper method for updating message fields
    private fun updateFields() {
        val message = intent.getStringExtra("text")
        val date = intent.getStringExtra("time")
        tvText.text = message
        tvDate.text = date
    }
}
