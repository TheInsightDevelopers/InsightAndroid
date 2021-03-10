package com.example.insights

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        @Suppress("DEPRECATION")

        Handler().postDelayed(
                {
                    startActivity(Intent(this, LoginPage::class.java))
                    finish()
                },
                2500
        )
    }


}