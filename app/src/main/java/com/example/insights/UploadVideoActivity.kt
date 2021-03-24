package com.example.insights

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore

class UploadVideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_video)
        findViewById<Button>(R.id.upload_video_btn).setOnClickListener {
            val title_video = findViewById<EditText>(R.id.video_upload_title).text.toString()
            val name = findViewById<EditText>(R.id.video_upload_name).text.toString()
            val comment = findViewById<EditText>(R.id.video_upload_comment).text.toString()
            val video_data = hashMapOf("Title" to title_video,
                                        "Name" to name,
                                            "Comment" to comment)
            FirebaseFirestore.getInstance().collection("Videos").document().set(video_data).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    finish()
                }
            }
        }
    }
}