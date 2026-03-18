package com.android.aquawatch.login

interface LoginContract {

    interface View {
        fun showEmailError()
        fun showPasswordError()
        fun showLoginFailed(message: String)
        fun navigateToHome()
    }

    interface Presenter {
        fun login(email: String, password: String)
    }
}