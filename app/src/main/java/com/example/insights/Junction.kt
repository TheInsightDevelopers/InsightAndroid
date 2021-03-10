package com.example.insights

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Junction : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_junction)
    }

    fun openInstructorSignUp(view: View) {
        startActivity(Intent(this@Junction, Instructor::class.java))
    }

    fun openStudentSignUp(view: View) {
        startActivity(Intent(this@Junction, Student::class.java))
    }
}