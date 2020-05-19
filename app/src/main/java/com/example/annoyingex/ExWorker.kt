package com.example.annoyingex

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.Worker
import androidx.work.WorkerParameters

class ExWorker(private val context: Context, workParams: WorkerParameters): Worker(context, workParams) {

    private val notificationManager = (applicationContext as AnnoyingExApp).annoyingExNotificationManager
    private val apiManager = (applicationContext as AnnoyingExApp).apiManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun doWork(): Result {
        Log.i("test", "test test test test test")

        notificationManager.postItNote(apiManager.returnRandomMessage())

        return Result.success()
    }

}