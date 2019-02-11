package com.example.antonynganga.androidfreckle.view.DashBoard

import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.antonynganga.androidfreckle.R
import com.example.antonynganga.androidfreckle.util.NetworkInfo
import com.example.antonynganga.androidfreckle.data.Constants.CONNECTIVITY_ACTION
import com.example.antonynganga.androidfreckle.data.SecretConstants
import com.example.antonynganga.androidfreckle.services.NetworkChangeReceiver
import com.github.ajalt.timberkt.Timber
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

class MainActivity : AppCompatActivity() {

    internal var intentFilter: IntentFilter? = null
    internal var receiver: NetworkChangeReceiver? = null

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver, intentFilter)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCenter.start(application,
            SecretConstants.APP_CENTER_SECRET,
            Analytics::class.java, Crashes::class.java)

        Timber.plant(Timber.DebugTree())

        intentFilter = IntentFilter()
        intentFilter?.let {
            intentFilter!!.addAction(CONNECTIVITY_ACTION)
            receiver = NetworkChangeReceiver()
        }

        if (NetworkInfo.getConnectivityStatus(this@MainActivity) > 0)
            Toast.makeText(this, "Connect", Toast.LENGTH_SHORT).show()

        else
            Toast.makeText(this, "No connection", Toast.LENGTH_SHORT).show()
    }

    fun addLogText(log: String) {
        Toast.makeText(this, log, Toast.LENGTH_SHORT).show()
    }
}
