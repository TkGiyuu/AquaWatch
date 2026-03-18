package com.android.aquawatch.Register

interface RegisterContract {

    interface View {
        fun showError(message: String)
        fun navigateToLogin()
    }

    interface Presenter {
        fun register(email: String, password: String, confirm: String)
    }
}