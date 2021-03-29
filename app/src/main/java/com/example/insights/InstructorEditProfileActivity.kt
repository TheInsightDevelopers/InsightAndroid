package com.example.insights

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class InstructorEditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_edit_profile_page)
        findViewById<Button>(R.id.SubmitButton).setOnClickListener {
            val name = findViewById<EditText>(R.id.instructor_profile_edit_name).text.toString()
            val job = findViewById<EditText>(R.id.instructor_profile_edit_currentjob).text.toString()
            val special = findViewById<EditText>(R.id.instructor_profile_edit_areaofspecialisation).text.toString()
            val degree = findViewById<EditText>(R.id.instructor_profile_edit_degree)
            val userData = hashMapOf("Name" to name,
                "Degree" to degree,
                "Job" to job,
                "Specialisation" to special,
                "type" to "Instructor")
            FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().currentUser.uid).set(userData).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this@InstructorEditProfileActivity,"Profile updated successfully",Toast.LENGTH_SHORT).show()
                    finish()
                }
                else{
                    Toast.makeText(this@InstructorEditProfileActivity,"An error Occurred",Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}