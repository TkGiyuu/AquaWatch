package com.android.aquawatch.Register

class RegisterPresenter(private val view: RegisterContract.View)
    : RegisterContract.Presenter {

    private val auth = null

    override fun register(email: String, password: String, confirm: String) {

        if (email.isEmpty() || password.length < 6) {
            view.showError("Invalid input")
            return
        }

        if (password != confirm) {
            view.showError("Passwords do not match")
            return
        }

    }
}