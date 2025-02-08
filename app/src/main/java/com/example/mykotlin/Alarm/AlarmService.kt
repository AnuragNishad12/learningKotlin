package com.example.mykotlin.Alarm

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.mykotlin.R

class AlarmService : Service() {


    companion object {
        const val CHANNEL_ID = "alarm_notification_channel"
    }

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()

        mediaPlayer = MediaPlayer.create(this, R.raw.alarmsound)
        mediaPlayer.isLooping = true
        mediaPlayer.start()


        createNotificationChannel()


        startForeground(1, buildNotification())
    }

    private fun createNotificationChannel() {
        // Check if the device is running on Android Oreo (API level 26) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create a NotificationChannel with an ID, name, and importance level
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Alarm Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel for Alarm notifications"
            }

            // Get the system's NotificationManager service
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Create the notification channel on the device
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun buildNotification(): Notification {
        // Create an intent for stopping the alarm
        val stopIntent = Intent(this, AlarmService::class.java).apply {
            action = "STOP_ALARM"
        }
        val stopPendingIntent = PendingIntent.getService(this, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Build the notification with a "Stop Alarm" action
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background) // Replace with your own icon
            .setContentTitle("Alarm Ringing")
            .setContentText("Tap to stop the alarm")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .addAction(R.drawable.ic_launcher_foreground, "Stop Alarm", stopPendingIntent)
            .setAutoCancel(true)
            .build()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Handle the stop alarm action
        if (intent?.action == "STOP_ALARM") {
            stopAlarm()
        }
        return START_STICKY
    }

    private fun stopAlarm() {
        mediaPlayer.stop()
        mediaPlayer.release()
        stopForeground(true)
        stopSelf()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
