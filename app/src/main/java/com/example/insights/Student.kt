package com.example.insights

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Student : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        findViewById<Button>(R.id.student_register_btn).setOnClickListener {
            val email = findViewById<EditText>(R.id.student_register_email).text.toString()
            val password = findViewById<EditText>(R.id.student_register_password).text.toString()
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    Toast.makeText(this@Student,"You have been registered successfully",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@Student,Main_page::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this@Student,"Registration Failed",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}