package com.android.aquawatch.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginPresenter(
    private val view: LoginContract.View
) : LoginContract.Presenter {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase
        .getInstance("https://esp32-android-project-423ca-default-rtdb.firebaseio.com/")
        .reference

    override fun login(username: String, password: String) {

        if (username.isEmpty()) {
            view.showUsernameError()
            return
        }

        if (password.length < 6) {
            view.showPasswordError()
            return
        }

        database.child("users").orderByChild("username").equalTo(username)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        var email = ""
                        for (child in snapshot.children) {
                            email = child.child("email").value.toString()
                            break
                        }
                        if (email.isNotEmpty()) {
                            auth.signInWithEmailAndPassword(email, password)
                                .addOnSuccessListener {
                                    view.navigateToHome()
                                }
                                .addOnFailureListener {
                                    view.showLoginFailed(it.message ?: "Login failed")
                                }
                        } else {
                            view.showLoginFailed("Email not found for username")
                        }
                    } else {
                        view.showLoginFailed("Username not found")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    view.showLoginFailed(error.message)
                }
            })
    }
}