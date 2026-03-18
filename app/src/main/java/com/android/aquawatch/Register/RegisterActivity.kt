package com.android.aquawatch.register

import  android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.aquawatch.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Register"

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase
            .getInstance("https://esp32-android-project-423ca-default-rtdb.firebaseio.com/")
            .reference

        etEmail = findViewById(R.id.etRegUsername)
        etPassword = findViewById(R.id.etRegPassword)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {

            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isEmpty()) {
                etEmail.error = "Email required"
                return@setOnClickListener
            }

            if (password.length < 6) {
                etPassword.error = "Password must be at least 6 characters"
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    val uid = auth.currentUser?.uid
                    if (uid == null) {
                        Toast.makeText(
                            this,
                            "Registration succeeded, but user id missing.",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                        return@addOnSuccessListener
                    }

                    val userNode = mapOf(
                        "email" to email.trim(),
                        "name" to email.substringBefore("@").replaceFirstChar { it.uppercaseChar() }
                    )

                    database.child("users")
                        .child(uid)
                        .setValue(userNode)
                        .addOnSuccessListener {
                            Toast.makeText(
                                this,
                                "Registration Successful!",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        }
                        .addOnFailureListener { exception ->
                            Toast.makeText(
                                this,
                                "Account created, but failed to save to database: ${exception.message}",
                                Toast.LENGTH_LONG
                            ).show()
                            // Still return to login so user flow is not blocked
                            finish()
                        }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
                }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}