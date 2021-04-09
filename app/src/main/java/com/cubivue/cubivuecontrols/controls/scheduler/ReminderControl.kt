package com.cubivue.cubivuecontrols.controls.scheduler

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.LiveData

class ReminderControl(private val context: Context) : LiveData<Long>() {

    companion object {
        private lateinit var instance: ReminderControl

        const val reminderIntent = "my.reminderIntent"
        const val reminderIntentCode = 2323422

        fun get(context: Context): ReminderControl {
            instance = if (::instance.isInitialized) {
                instance
            } else {
                ReminderControl(context)
            }
            return instance
        }
    }

    private val reminderBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            if (intent.action != null) {

                if (intent.action == reminderIntent) {
                    value = System.currentTimeMillis()
                }
            }
        }
    }

    override fun onActive() {
        super.onActive()

        value = System.currentTimeMillis()

        val broadcastIntent = IntentFilter()
        broadcastIntent.addAction(reminderIntent)
        context.registerReceiver(reminderBroadcastReceiver, broadcastIntent)
    }

    override fun onInactive() {
        super.onInactive()
        context.unregisterReceiver(reminderBroadcastReceiver)
    }
}