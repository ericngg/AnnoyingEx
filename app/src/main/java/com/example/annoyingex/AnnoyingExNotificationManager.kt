package com.example.annoyingex

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class AnnoyingExNotificationManager(private val context: Context) {

    private val notificationManagerCompat = NotificationManagerCompat.from(context)
    private var index : Int = 100

    companion object {
        const val CHANNEL_ID = "TEXT_NOTIFICATION"
    }

    init {
        createNotificationChannel()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun postItNote(content: String) {
        val mainActivity = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("text", content)
            putExtra("time", "${LocalDateTime.now()}")
        }

        val pendingIntent = PendingIntent.getActivity(context, 0, mainActivity, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.sms)
            .setContentTitle("Kathy Kim")
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManagerCompat.notify(index, notification)
        index = index.inc()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Text Notifications"
            val descriptionText = "Texts from an Ex"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            notificationManagerCompat.createNotificationChannel(channel)
        }
    }

}