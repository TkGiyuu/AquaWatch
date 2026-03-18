package com.android.aquawatch.login

import com.google.firebase.auth.FirebaseAuth

class LoginPresenter(
    private val view: LoginContract.View
) : LoginContract.Presenter {

    private val auth = FirebaseAuth.getInstance()

    override fun login(email: String, password: String) {

        if (email.isEmpty()) {
            view.showEmailError()
            return
        }

        if (password.length < 6) {
            view.showPasswordError()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                view.navigateToHome()
            }
            .addOnFailureListener {
                view.showLoginFailed(it.message ?: "Login failed")
            }
    }
}