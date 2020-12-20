package com.example.aadpracticeproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.aadpracticeproject.Utils.EXTRA_NOTIFICATION_ID

class NotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action){

            Utils.NOTIFICATION_ACTION->{
                val dat = intent.getIntExtra(EXTRA_NOTIFICATION_ID,100)
                Toast.makeText(context,"TESTING BROADCAST  $dat",Toast.LENGTH_LONG).show()
            }


        }
    }
}