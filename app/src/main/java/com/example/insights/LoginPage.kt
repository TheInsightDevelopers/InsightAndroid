package com.example.insights

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        val forgotBtn = findViewById<TextView>(R.id.tvForgotPassword)
        forgotBtn.setOnClickListener {
            startActivity(Intent(this@LoginPage, ForgotPasswordActivity::class.java))
        }
        val loginBtn = findViewById<Button>(R.id.loginButton)
        loginBtn.setOnClickListener {
            val email = findViewById<EditText>(R.id.login_email_id).text.toString()
            val password = findViewById<EditText>(R.id.login_password).text.toString()
            //Login with firebase
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@LoginPage, "You have been logged in.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginPage,mainPageActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@LoginPage, "Login Failed.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun openJunction(view: View) {
        startActivity(Intent(this@LoginPage, Junction::class.java))
    }


}