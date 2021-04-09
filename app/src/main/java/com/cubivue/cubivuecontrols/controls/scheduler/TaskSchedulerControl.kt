package com.cubivue.cubivuecontrols.controls.scheduler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent


object TaskSchedulerControl {

    fun scheduleRepetitiveTask(context: Context) {

        val delay = getMilliSecondsForReminderSelected()

        val intent = Intent(ReminderControl.reminderIntent)

        val pendingIntent = PendingIntent.getBroadcast(context, ReminderControl.reminderIntentCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarm = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + delay, delay, pendingIntent)
    }

    fun stopScheduler(context: Context) {

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?

        val intent = Intent(context, ReminderControl::class.java)
        intent.action = ReminderControl.reminderIntent

        val alarmIntent = PendingIntent.getBroadcast(context, ReminderControl.reminderIntentCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager?.cancel(alarmIntent)
    }

    private fun getMilliSecondsForReminderSelected(): Long {
        return (60 * 1000)
    }
}