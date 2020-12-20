package com.example.aadpracticeproject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.aadpracticeproject.Utils.EXTRA_NOTIFICATION_ID
import com.example.aadpracticeproject.Utils.NOTIFICATION_ACTION

class MainActivity : AppCompatActivity() {

    val CHANNEL_ID = "MY ID"
    private lateinit var notificationManager: NotificationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var image: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.image)

        val button = findViewById<Button>(R.id.button)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        button.setOnClickListener {
        createNotificationChannel()
        }
    }

    private fun createNotificationChannel() {

        val intent = Intent(this, NotificationReceiver::class.java).apply {
            action = NOTIFICATION_ACTION
            putExtra(EXTRA_NOTIFICATION_ID, 201321)
        }
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText

            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            val builder = NotificationCompat.Builder(this, "MK")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Testing on Oreo ")
                .setContentText("HELLO mikail we are testing the notification in Android OREO")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()
            notificationManager.notify(2, builder)


        } else {
            val builder = NotificationCompat.Builder(this, "MK")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Testing Notification")
                .setContentText("HELLO mikail we are testing the notification")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(R.drawable.notification, getString(R.string.snooze),
                    pendingIntent)
                .setAutoCancel(true)
                .build()
            notificationManager.notify(2, builder)
        }
    }

}