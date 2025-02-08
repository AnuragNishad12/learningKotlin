package com.example.mykotlin.Alarm


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.mykotlin.R


class AlarmReceiver : BroadcastReceiver() {
    companion object {
        var mediaPlayer: MediaPlayer? = null
    }

    override fun onReceive(context: Context, intent: Intent?) {
        if (mediaPlayer == null) {

            mediaPlayer = MediaPlayer.create(context, R.raw.alarmsound)
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
        }

        createNotificationManager(context)

        if (intent?.action == "STOP_ALARM") {
            val newIntent = Intent(context, ProblemSolved::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(newIntent)
        }
    }




    fun createanotificationChannel(context: Context){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val name = "Notification Namr"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel("mYNOTIFY",name,importance)

            val notificationManager: NotificationManager =
                context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotificationManager(context: Context) {
        createanotificationChannel(context)


        val activityIntent = Intent(context, ProblemSolved::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(
                context,
                0,
                activityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE  // Added FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getActivity(
                context,
                0,
                activityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }


        val stopIntent = Intent(context, AlarmReceiver::class.java).apply {
            action = "STOP_ALARM"
        }
        val stopPendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getBroadcast(
                context,
                1,
                stopIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE  // Added FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getBroadcast(
                context,
                1,
                stopIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        val notificationBuilder = NotificationCompat.Builder(context, "mYNOTIFY")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Alarm Title")
            .setContentText("Alarm is ringing!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .addAction(android.R.drawable.ic_media_pause, "Stop Alarm", stopPendingIntent)
            .setAutoCancel(true)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notificationBuilder.build())
    }



}















//class AlarmReceiver : BroadcastReceiver() {
//    companion object {
//        private var mediaPlayer: MediaPlayer? = null
//        private const val NOTIFICATION_ID = 123
//        private const val CHANNEL_ID = "alarm_channel"
//        private const val ACTION_STOP_ALARM = "ACTION_STOP_ALARM"
//    }
//
//    override fun onReceive(context: Context, intent: Intent?) {
//        when (intent?.action) {
//            ACTION_STOP_ALARM -> {
//                stopAlarm(context)
//            }
//            else -> {
//                startAlarm(context)
//            }
//        }
//    }
//
//    private fun startAlarm(context: Context) {
//        Toast.makeText(context, "⏰ Alarm Ringing!", Toast.LENGTH_LONG).show()
//
//
//        if (mediaPlayer == null) {
//            mediaPlayer = MediaPlayer.create(context.applicationContext, R.raw.alarmsound).apply {
//                isLooping = true
//                start()
//            }
//        }
//
//        // Create stop intent
//        val stopIntent = Intent(context, AlarmReceiver::class.java).apply {
//            action = ACTION_STOP_ALARM
//        }
//
//        val pendingIntent = PendingIntent.getBroadcast(
//            context,
//            0,
//            stopIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//        )
//
//        // Create notification manager and channel
//        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        // Create notification channel for Android 8.0+
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                CHANNEL_ID,
//                "Alarms",
//                NotificationManager.IMPORTANCE_HIGH
//            ).apply {
//                setSound(null, null) // Prevent notification sound since we're using MediaPlayer
//                enableVibration(true)
//            }
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        // Build and show notification
//        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
//            .setContentTitle("Alarm Alert!")
//            .setContentText("Tap to stop the alarm")
//            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setCategory(NotificationCompat.CATEGORY_ALARM)
//            .setOngoing(true) // Make notification persistent
//            .addAction(R.drawable.ic_launcher_foreground, "Stop", pendingIntent)
//            .build()
//
//        notificationManager.notify(NOTIFICATION_ID, notification)
//    }
//
//    private fun stopAlarm(context: Context) {
//        // Stop and release MediaPlayer
//        mediaPlayer?.apply {
//            stop()
//            release()
//        }
//        mediaPlayer = null
//
//
//        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.cancel(NOTIFICATION_ID)
//    }
//}