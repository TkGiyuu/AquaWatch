package com.android.aquawatch.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.aquawatch.R
import com.android.aquawatch.alerts.AlertsActivity
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

        btnSimulate.setOnClickListener {
            presenter.simulateWater()
        }

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