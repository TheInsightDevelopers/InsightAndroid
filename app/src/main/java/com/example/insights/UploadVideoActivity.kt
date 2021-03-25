package com.example.insights

import android.app.Activity
import android.content.Intent
import android.content.Intent.*
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.firestore.FirebaseFirestore
import java.util.jar.Manifest
import android.Manifest.permission.READ_EXTERNAL_STORAGE as READ_EXTERNAL_STORAGE1
private var filePath: Uri? = null
class UploadVideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_video)
        var selectedVideo: Uri
    }

    fun select_video_upload(view: View) {
        if (ContextCompat.checkSelfPermission(
                this,
                READ_EXTERNAL_STORAGE1
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            //Select Video
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE1), 111)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111) {
            val galleryIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, 333)
        } else {
            Toast.makeText(
                this,
                "Permission for Storage Access isn't granted, You can do it in Settings",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == Activity.RESULT_OK){
            if(requestCode == 333){
                if(data != null){
                    filePath = data.data
                }
            }
    }
        fun create_book_data(view: View) {
            val title_video = findViewById<EditText>(R.id.video_upload_title).text.toString()
            val name = findViewById<EditText>(R.id.video_upload_name).text.toString()
            val comment = findViewById<EditText>(R.id.video_upload_comment).text.toString()
            val video_data = hashMapOf(
                "Title" to title_video,
                "Name" to name,
                "Comment" to comment
            )
            FirebaseFirestore.getInstance().collection("Videos").document().set(video_data)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        finish()
                    }
                }
        }
    }

}