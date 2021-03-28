package com.example.insights

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class BookUploadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_upload)
        val storage = Firebase.storage
                
    }
}