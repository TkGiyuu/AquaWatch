package com.android.aquawatch.home

interface HomeContract {

    interface View {
        fun showWaterLevel(level: Int)
    }

    interface Presenter {
        fun simulateWater()
    }
}