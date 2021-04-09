package com.cubivue.cubivuecontrols

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cubivue.cubivuecontrols.controls.scheduler.TaskSchedulerControl

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onResume() {
        super.onResume()

        //Run Scheduler
        TaskSchedulerControl.scheduleRepetitiveTask(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        //Stop Scheduler
        TaskSchedulerControl.stopScheduler(this)
    }
}