package com.example.insights

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        @Suppress("DEPRECATION")

        Handler().postDelayed(
            {
                if (FirebaseAuth.getInstance().currentUser == null) {
                    startActivity(Intent(this, LoginPage::class.java))
                    finish()
                } else {
                    startActivity(Intent(this, mainPageNew::class.java))
                    finish()
                }
            },
            500
        )
    }


}