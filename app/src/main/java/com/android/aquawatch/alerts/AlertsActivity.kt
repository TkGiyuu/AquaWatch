package com.android.aquawatch.alerts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.aquawatch.R

class AlertsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alerts)

        supportActionBar?.title = "Alerts"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}

