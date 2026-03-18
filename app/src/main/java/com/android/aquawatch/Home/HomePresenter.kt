package com.android.aquawatch.home

class HomePresenter(
    private val view: HomeContract.View
) : HomeContract.Presenter {

    override fun simulateWater() {
        val level = (10..100).random()
        view.showWaterLevel(level)
    }
}