package com.example.insights

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Student : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        findViewById<Button>(R.id.student_register_btn).setOnClickListener {
            val progressBar = Dialog(this)
            progressBar.setContentView(R.layout.progress_bar)
            val email = findViewById<EditText>(R.id.student_register_email).text.toString()
            val password = findViewById<EditText>(R.id.student_register_password).text.toString()
            val name = findViewById<EditText>(R.id.student_register_name).text.toString()
            val classname = findViewById<EditText>(R.id.student_register_class).text.toString()
            val school = findViewById<EditText>(R.id.student_register_school).text.toString()
            val favSubject =
                findViewById<EditText>(R.id.student_register_favSubject).text.toString()
            val data = hashMapOf(
                "Name" to name,
                "Class" to classname,
                "Fav_subject" to favSubject,
                "School" to school,
                "type" to "Student"
            )
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val db = FirebaseFirestore.getInstance()
                        db.collection("Users").document(FirebaseAuth.getInstance().currentUser.uid)
                            .set(data).addOnCompleteListener { task1 ->
                            if (task1.isSuccessful) {
                                Toast.makeText(
                                    this@Student,
                                    "Your details have saved successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(Intent(this@Student, mainPageNew::class.java))
                                progressBar.hide()
                                finish()


                            } else {
                                Toast.makeText(
                                    this@Student,
                                    "An error occurred",
                                    Toast.LENGTH_SHORT
                                ).show()
                                progressBar.hide()

                            }
                        }

                    } else {
                        Toast.makeText(this@Student, "Registration Failed", Toast.LENGTH_SHORT)
                            .show()
                        progressBar.hide()
                    }
                }
        }
    }
}