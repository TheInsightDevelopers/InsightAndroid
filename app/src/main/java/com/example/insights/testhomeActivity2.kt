package com.example.insights

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class testhomeActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testhome2)
        findViewById<TextView>(R.id.tv_home_user_id).text = "Your User id ${FirebaseAuth.getInstance().currentUser!!.uid}"
        findViewById<TextView>(R.id.emailId_home).text = "Your email id: ${FirebaseAuth.getInstance().currentUser.email}"
        findViewById<Button>(R.id.signOut_button).setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@testhomeActivity2,LoginPage::class.java))
            finish()
            Toast.makeText(this@testhomeActivity2,"You have been Logged out",Toast.LENGTH_SHORT).show()
        }
    }
}