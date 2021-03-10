package com.example.insights

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Junction : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_junction)
    }
    fun openInstructorSignUp(view:View){
        startActivity(Intent(this@Junction,Instructor::class.java))
    }
    fun openStudentSignUp(view:View){
        startActivity(Intent(this@Junction,Student::class.java))
    }
}