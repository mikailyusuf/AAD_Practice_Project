package com.example.aadpracticeproject

import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.aadpracticeproject.Utils.LOG_MESSAGE
import com.example.aadpracticeproject.Utils.makeStatusNotification

class BlurWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    private lateinit var notificationManager: NotificationManager
    override fun doWork(): Result {
        val appContext = applicationContext
        makeStatusNotification("Worker Has started",appContext)

        return try {
            for (i in 1..1000) {
                Log.e(LOG_MESSAGE, i.toString())
                val message = inputData.getString(WORKER_KEY)
                if (message != null) {
                    makeStatusNotification(message,appContext)
                }
                Result.success()

            }
            Result.success()

        } catch (e: Throwable) {
            Log.e(LOG_MESSAGE,e.toString())
            Result.failure()

        }

    }



}