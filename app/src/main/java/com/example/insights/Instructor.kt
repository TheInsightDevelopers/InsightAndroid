package com.example.insights

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Instructor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor)
        findViewById<Button>(R.id.instructor_register_btn).setOnClickListener {
            val progressBar = Dialog(this)
            progressBar.setContentView(R.layout.progress_bar)
            progressBar.show()
            val email = findViewById<EditText>(R.id.instructor_register_email).text.toString()
            val password = findViewById<EditText>(R.id.instructor_register_password).text.toString()
            val confirmPassword =
                findViewById<EditText>(R.id.instructor_register_password_con).text.toString()
            val name = findViewById<EditText>(R.id.instructor_register_name).text.toString()
            val degree = findViewById<EditText>(R.id.instructor_register_degree).text.toString()
            val job = findViewById<EditText>(R.id.instructor_register_job).text.toString()
            val special = findViewById<EditText>(R.id.instructor_register_special).text.toString()

            //if statements to check for null values
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(
                    this@Instructor,
                    "You did not provide a name",
                    Toast.LENGTH_SHORT
                ).show()
                progressBar.hide()
            } else if (TextUtils.isEmpty(email)) {
                Toast.makeText(
                    this@Instructor,
                    "You did not provide an email",
                    Toast.LENGTH_SHORT
                ).show()
                progressBar.hide()
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(
                    this@Instructor,
                    "You did not provide a password",
                    Toast.LENGTH_SHORT
                ).show()
                progressBar.hide()
            } else if (TextUtils.isEmpty(confirmPassword)) {
                Toast.makeText(
                    this@Instructor,
                    "You did not confirm your password",
                    Toast.LENGTH_SHORT
                ).show()
                progressBar.hide()
            } else if (TextUtils.isEmpty(degree)) {
                Toast.makeText(
                    this@Instructor,
                    "You did not provide a degree",
                    Toast.LENGTH_SHORT
                ).show()
                progressBar.hide()
            } else if (TextUtils.isEmpty(job)) {
                Toast.makeText(
                    this@Instructor,
                    "You did not provide a current job",
                    Toast.LENGTH_SHORT
                ).show()
                progressBar.hide()
            } else if (TextUtils.isEmpty(special)) {
                Toast.makeText(
                    this@Instructor,
                    "You did not provide an area of specialisation",
                    Toast.LENGTH_SHORT
                ).show()
                progressBar.hide()
                //when no value is empty this else is used
            } else {
                if (password.length >= 6) {
                    if (password == confirmPassword) {
                        val data = hashMapOf(
                            "Name" to name,
                            "Degree" to degree,
                            "Job" to job,
                            "Specialisation" to special,
                            "type" to "Instructor"
                        )
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    FirebaseFirestore.getInstance().collection("Users")
                                        .document(FirebaseAuth.getInstance().currentUser.uid)
                                        .set(data)
                                        .addOnCompleteListener { task1 ->
                                            if (task1.isSuccessful) {
                                                progressBar.hide()
                                                Toast.makeText(
                                                    this@Instructor,
                                                    "Your details have saved successfully",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                startActivity(
                                                    Intent(
                                                        this@Instructor,
                                                        mainPageNew::class.java
                                                    )
                                                )
                                                finish()
                                            } else {
                                                progressBar.hide()
                                                Toast.makeText(
                                                    this@Instructor,
                                                    "An error occurred",
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                            }
                                        }
                                } else {
                                    progressBar.hide()
                                    Toast.makeText(
                                        this@Instructor,
                                        "Registration Failed",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }
                    } else {
                        Toast.makeText(
                            this@Instructor,
                            "The entered Passwords doesn't match",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@Instructor,
                        "Password Must contain 6 Characters",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}