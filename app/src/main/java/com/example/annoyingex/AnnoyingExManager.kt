package com.example.annoyingex

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class AnnoyingExManager(context: Context) {

    private var workManager = WorkManager.getInstance(context)

    companion object {
        const val ANNOYING_EX_TAG = "ANNOYING_EX_TAG"
        const val ANNOYING_EX_EXTRA_TAG = "ANNOYING_EX_EXTRA_TAG"
    }

    // starts the working manager
    fun startFunction() {
        if (!isRunning(ANNOYING_EX_TAG)) {
            stopWork()
        }

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<ExWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .addTag(ANNOYING_EX_TAG)
            .build()

        workManager.enqueue(workRequest)
    }

    // Returns whether or not if the work manager is running
    private fun isRunning(TAG: String): Boolean {
        return when (workManager.getWorkInfosByTag(TAG).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
            WorkInfo.State.ENQUEUED -> true
            else -> false
        }
    }

    // Stops the work manager
    fun stopWork() {
        workManager.cancelAllWorkByTag(ANNOYING_EX_TAG)
        workManager.cancelAllWorkByTag(ANNOYING_EX_EXTRA_TAG)
    }

    // starts the working manager
    fun startExtraFunction() {
        if (!isRunning(ANNOYING_EX_EXTRA_TAG)) {
            stopWork()
        }

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<ExtraExWorker>(2, TimeUnit.DAYS)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .addTag(ANNOYING_EX_EXTRA_TAG)
            .build()

        workManager.enqueue(workRequest)
    }

}