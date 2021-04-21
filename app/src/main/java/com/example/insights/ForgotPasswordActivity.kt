package com.example.insights

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        val resetBtn = findViewById<Button>(R.id.reset_password_btn)
        resetBtn.setOnClickListener {
            val email = findViewById<EditText>(R.id.editText_email_forgot).text.toString()
            if (email.isEmpty()) {
                Toast.makeText(
                    this@ForgotPasswordActivity,
                    "Please Enter the email",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this@ForgotPasswordActivity,
                                "Reset Password has been sent to the given email",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this@ForgotPasswordActivity,
                                "An error Occured",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
            }


        }
    }
}

