package com.example.mykotlin.Alarm

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.mykotlin.R

class AlarmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        val setAlarmButton = findViewById<Button>(R.id.setAlarmButton)
        setAlarmButton.setOnClickListener {
            setAlarm()
        }

    }


    private fun setAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val triggerTime = System.currentTimeMillis() + 5000 // 1 minute later
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
        Toast.makeText(this, "✅ Alarm Set!", Toast.LENGTH_SHORT).show()
    }

}