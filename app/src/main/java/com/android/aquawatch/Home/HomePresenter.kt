package com.android.aquawatch.home

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomePresenter(
    private val view: HomeContract.View
) : HomeContract.Presenter {

    private val database = FirebaseDatabase.getInstance()
    // Listening directly to the path the ESP32 writes: /waterLevel
    private val waterLevelRef = database.getReference("waterLevel")

    override fun startListening() {
        waterLevelRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Instantly updating UI whenever ESP32 data arrives
                val level = snapshot.getValue(Int::class.java) ?: 0
                view.showWaterLevel(level)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseData", "Database read failed: ${error.message}")
            }
        })
    }
}