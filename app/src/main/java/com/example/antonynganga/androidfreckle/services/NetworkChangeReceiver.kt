package com.example.antonynganga.androidfreckle.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

import com.example.antonynganga.androidfreckle.view.DashBoard.MainActivity
import com.example.antonynganga.androidfreckle.data.Constants
import com.example.antonynganga.androidfreckle.util.NetworkInfo

class NetworkChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val status = NetworkInfo.getConnectivityStatusString(context)

        Log.e("Receiver ", "" + status)

        if (status == Constants.NOT_CONNECT) {
            Toast.makeText(context, "not connection", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "connected to internet", Toast.LENGTH_SHORT).show()
            Log.e("Receiver ", "connected to internet") // your code when internet connection come back
        }
        (context as MainActivity).addLogText(status)
    }
}
