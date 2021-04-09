package com.cubivue.cubivuecontrols

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.cubivue.cubivuecontrols.controls.scheduler.ReminderControl
import com.cubivue.cubivuecontrols.controls.sound.BeeperControl
import com.cubivue.cubivuecontrols.utils.getReadableTime
import kotlinx.android.synthetic.main.fragment_control_list.*

class ControlListFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_control_list, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get Call-back from Scheduler
        ReminderControl.get(requireContext()).observe(viewLifecycleOwner, Observer {
            textview_first?.text = "Event Received at: ${getReadableTime(it)}"
            BeeperControl(requireContext()).play()
        })
    }
}