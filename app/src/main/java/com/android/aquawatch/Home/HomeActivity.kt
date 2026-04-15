package com.android.aquawatch.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.aquawatch.R
import com.android.aquawatch.Alerts.AlertsActivity
import com.android.aquawatch.profile.ProfileActivity

class HomeActivity : AppCompatActivity(), HomeContract.View {

    private lateinit var presenter: HomePresenter
    private lateinit var tvWater: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnSimulate: Button
    private lateinit var btnProfile: Button
    private lateinit var btnAlerts: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        presenter = HomePresenter(this)

        tvWater = findViewById(R.id.tvWaterLevel)
        progressBar = findViewById(R.id.progressWater)
        btnSimulate = findViewById(R.id.btnSimulate)
        btnProfile = findViewById(R.id.btnProfile)
        btnAlerts = findViewById(R.id.btnAlerts)

        // The hardware now provides live data, so the simulate button is no longer needed.
        btnSimulate.setOnClickListener {
            android.widget.Toast.makeText(this, "Receiving live hardware data!", android.widget.Toast.LENGTH_SHORT).show()
        }

        // Connect to Firebase for real-time ESP32 data right away
        presenter.startListening()

        btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        btnAlerts.setOnClickListener {
            startActivity(Intent(this, AlertsActivity::class.java))
        }
    }

    override fun showWaterLevel(level: Int) {
        tvWater.text = "Water Level: $level%"
        progressBar.progress = level
    }
}