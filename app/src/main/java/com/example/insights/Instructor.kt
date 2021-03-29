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
            val name = findViewById<EditText>(R.id.instructor_register_name).text.toString()
            val degree = findViewById<EditText>(R.id.instructor_register_degree).text.toString()
            val job = findViewById<EditText>(R.id.instructor_register_job).text.toString()
            val special = findViewById<EditText>(R.id.instructor_register_special).text.toString()
            val data = hashMapOf(
                "Name" to name,
                "Degree" to degree,
                "Job" to job,
                "Specialisation" to special,
                "type" to "Instructor"
            )
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if(task.isSuccessful){
   FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().currentUser.uid).set(data).addOnCompleteListener { task1 ->
                        if(task1.isSuccessful){
                            progressBar.hide()
                            Toast.makeText(this@Instructor,"Your details have saved successfully",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@Instructor,mainPageNew::class.java))
                            finish()
                        }
                        else{
                            progressBar.hide()
                            Toast.makeText(this@Instructor,"An error occurred",Toast.LENGTH_SHORT).show()

                        }
                    }


                }
                else{
                    progressBar.hide()
                    Toast.makeText(this@Instructor,"Registration Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}