package com.android.aquawatch.login

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.aquawatch.R
import com.android.aquawatch.home.HomeActivity
import com.android.aquawatch.register.RegisterActivity

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var presenter: LoginPresenter
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Login"

        presenter = LoginPresenter(this)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        val btnGoRegister = findViewById<Button>(R.id.btnGoRegister)

        btnGoRegister.setOnClickListener {
            startActivity(
                Intent(this, RegisterActivity::class.java)
            )
        }

        btnLogin.setOnClickListener {
            presenter.login(
                etUsername.text.toString(),
                etPassword.text.toString()
            )
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

    override fun showUsernameError() {
        etUsername.error = "Username required"
    }

    override fun showPasswordError() {
        etPassword.error = "Password must be at least 6 characters"
    }

    override fun showLoginFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }


}