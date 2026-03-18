package com.android.aquawatch.home

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.aquawatch.R

class HomeActivity : AppCompatActivity(), HomeContract.View {

    private lateinit var presenter: HomePresenter
    private lateinit var tvWater: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnSimulate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        presenter = HomePresenter(this)

        tvWater = findViewById(R.id.tvWaterLevel)
        progressBar = findViewById(R.id.progressWater)
        btnSimulate = findViewById(R.id.btnSimulate)

        btnSimulate.setOnClickListener {
            presenter.simulateWater()
        }
    }

    override fun showWaterLevel(level: Int) {
        tvWater.text = "Water Level: $level%"
        progressBar.progress = level
    }
}