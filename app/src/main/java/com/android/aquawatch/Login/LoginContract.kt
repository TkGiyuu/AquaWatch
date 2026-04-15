package com.android.aquawatch.login

interface LoginContract {

    interface View {
        fun showUsernameError()
        fun showPasswordError()
        fun showLoginFailed(message: String)
        fun navigateToHome()
    }

    interface Presenter {
        fun login(username: String, password: String)
    }
}