package com.example.annoyingex

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class AnnoyingExManager(context: Context) {

    private var workManager = WorkManager.getInstance(context)

    companion object {
        const val ANNOYING_EX_TAG = "ANNOYING_EX_TAG"
    }

    fun startFunction() {
        if (!isRunning()) {
            stopWork()
        }

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<ExWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .addTag(ANNOYING_EX_TAG)
            .build()

        workManager.enqueue(workRequest)
    }

    private fun isRunning(): Boolean {
        return when (workManager.getWorkInfosByTag(ANNOYING_EX_TAG).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
            WorkInfo.State.ENQUEUED -> true
            else -> false
        }
    }

    fun stopWork() {
        workManager.cancelAllWorkByTag(ANNOYING_EX_TAG)
    }

}