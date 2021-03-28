package com.example.insights

import android.app.Activity
import android.app.Dialog
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
import android.widget.VideoView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.io.IOException
import java.net.URL
import java.util.jar.Manifest
import android.Manifest.permission.READ_EXTERNAL_STORAGE as READ_EXTERNAL_STORAGE1

class UploadVideoActivity : AppCompatActivity() {
    private var filePath: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_video)
        findViewById<Button>(R.id.upload_video_btn).visibility = View.GONE
        findViewById<Button>(R.id.select_video_upload_btn).setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    READ_EXTERNAL_STORAGE1
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val galleryIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent, 333)
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE1), 111)
            }
        }
        var selectedVideo: Uri
        findViewById<Button>(R.id.upload_video_btn).setOnClickListener {
            if(filePath != null){
                val progressBarDialog = Dialog(this)
                progressBarDialog.setContentView(R.layout.progress_bar)
                progressBarDialog.show()
                val dRef : StorageReference =   FirebaseStorage.getInstance().reference.child("Video"+System.currentTimeMillis())
                dRef.putFile(filePath!!).addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener { url ->
                        val  videoUrl = url
                        val title_video = findViewById<EditText>(R.id.video_upload_title).text.toString()
                        val name = findViewById<EditText>(R.id.video_upload_name).text.toString()
                        val comment = findViewById<EditText>(R.id.video_upload_comment).text.toString()
                        val video_data = hashMapOf(
                            "Title" to title_video,
                            "Name" to name,
                            "Comment" to comment,
                            "VideoUrl" to videoUrl.toString()
                        )
                        progressBarDialog.hide()
                        FirebaseFirestore.getInstance().collection("Videos").document(System.currentTimeMillis()
                            .toString()).set(video_data)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(this@UploadVideoActivity,"Upload successful",Toast.LENGTH_SHORT).show()
                                    finish()
                                }
                            }
                    }
                }
            }
            else{
                Toast.makeText(this@UploadVideoActivity,"Url missing "+ filePath,Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun select_video_upload(view: View) {

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

            if(requestCode == 333){
                if(data != null){

                    try{
                        filePath = data.data!!
                        findViewById<Button>(R.id.upload_video_btn).visibility = View.VISIBLE
                    }
                    catch (e : IOException){
                        e.printStackTrace()
                        Toast.makeText(this@UploadVideoActivity,"error making local link",Toast.LENGTH_SHORT).show()
                    }
                }

            }

    }
    fun create_book_data(view: View) {

    }
}