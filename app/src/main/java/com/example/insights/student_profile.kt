package com.example.insights

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.FirebaseStorage.*
import com.google.firebase.storage.StorageReference
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class student_profile : AppCompatActivity() {
    val GALLERY_REQUEST_CODE = 3337
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_profile)
        dataFetch()
        findViewById<Button>(R.id.update_profile_image_btn).setOnClickListener {
            pickFromGallery()
        }
        /***************************Edit Profile start*****************************/
        findViewById<Button>(R.id.stud_profile_update_btn_act).setOnClickListener {
            startActivity(Intent(this@student_profile, studentEditProfile::class.java))
        }

    }


    /************************Delete Account ******************************/
    fun deleteAccount(view: View) {
        FirebaseFirestore.getInstance().collection("Users")
            .document(FirebaseAuth.getInstance().currentUser.uid).delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    FirebaseAuth.getInstance().currentUser.delete().addOnCompleteListener { task2 ->
                        if (task2.isSuccessful) {
                            Toast.makeText(
                                this@student_profile,
                                "Your account and data has been deleted",
                                Toast.LENGTH_SHORT
                            ).show()
                            startActivity(Intent(this@student_profile, LoginPage::class.java))
                            finish()
                        }
                    }
                } else {
                    Toast.makeText(this@student_profile, "An error Occured", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    /************************Upload Video******************************/
    fun startVideoUpload(view: View) {
        startActivity(Intent(this@student_profile, UploadVideoActivity::class.java))
    }

    /*************************************Upload Book**********************************/
    fun startBookUpload(view: View) {
        startActivity(Intent(this@student_profile, BookUploadActivity::class.java))
    }

    /*******************************Profile Image*****************************************/
    private fun pickFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        val mimeTypes = arrayOf("image/jpeg", "image/png", "image/jpg")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                data?.data?.let { uri ->
                    launchImageCrop(uri)
                }
            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                setImage(result.uri)
            }
        }
    }

    private fun setImage(uri: Uri?) {
        val dRef: StorageReference = FirebaseStorage.getInstance().reference
        val uid = FirebaseAuth.getInstance().currentUser.uid.toString()
        if (uri != null) {
            dRef.child(
                "ProfileImage" + uid + System.currentTimeMillis()
                    .toString()
            ).putFile(uri).addOnSuccessListener { taskSnapshot ->
                taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener { url ->
                    Log.d("urlcheck", "passed this")

                    val profileHash = hashMapOf("url" to url.toString())
                    FirebaseFirestore.getInstance()
                        .collection("ProfileImages")
                        .document(uid.toString())
                        .set(profileHash).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                dataFetch()
                            }
                        }
                }

            }
        }
    }

    private fun launchImageCrop(uri: Uri) {
        CropImage.activity(uri)
            .setGuidelines(CropImageView.Guidelines.ON)
            .setAspectRatio(1080, 1080)
            .setCropShape(CropImageView.CropShape.RECTANGLE)
            .start(this)
    }

    private fun dataFetch() {
        val uid = FirebaseAuth.getInstance().currentUser.uid
        val db = FirebaseFirestore.getInstance().collection("Users")
            .document(uid)
        db.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val hashMapData = document.data
                    val name = hashMapData?.get("Name")
                    val classname = hashMapData?.get("Class")
                    val favSubject = hashMapData?.get("Fav_subject")
                    val school = hashMapData?.get("School")
                    val email = FirebaseAuth.getInstance().currentUser.email
                    findViewById<TextView>(R.id.student_profile_name).text = name as CharSequence?
                    findViewById<TextView>(R.id.student_profile_email).text = email
                    findViewById<TextView>(R.id.student_profile_class).text =
                        classname as CharSequence?
                    findViewById<TextView>(R.id.student_profile_fav_subject).text =
                        favSubject as CharSequence?
                    findViewById<TextView>(R.id.student_profile_school).text =
                        school as CharSequence?
                    val profileImageView: ImageView = findViewById(R.id.profile_image_view)
                    FirebaseFirestore.getInstance()
                        .collection("ProfileImages")
                        .document(uid.toString()).get()
                        .addOnSuccessListener { ImageDocument ->
                            if (ImageDocument != null) {
                                val imageUrl = ImageDocument.data?.get("url").toString()
                                Glide.with(profileImageView)
                                    .load(imageUrl)
                                    .centerCrop()
                                    .placeholder(R.drawable.send_ic)
                                    .error(R.drawable.ic_profile_btn)
                                    .fallback(R.drawable.ic_baseline_menu_book_24)
                                    .into(profileImageView)
                            }
                        }


                }
            }
    }


}