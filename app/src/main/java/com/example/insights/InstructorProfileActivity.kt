package com.example.insights

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class InstructorProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_profile_page)
        findViewById<Button>(R.id.instructor_profile_uploadvideo).setOnClickListener {
            startActivity(Intent(this@InstructorProfileActivity, UploadVideoActivity::class.java))
        }
        findViewById<Button>(R.id.instructor_profile_uploadbook).setOnClickListener {
            startActivity(Intent(this@InstructorProfileActivity, BookUploadActivity::class.java))
        }
        findViewById<Button>(R.id.instructor_profile_signout).setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@InstructorProfileActivity, LoginPage::class.java))
            finish()
            Toast.makeText(
                this@InstructorProfileActivity,
                "You have been Logged Out",
                Toast.LENGTH_SHORT
            ).show()
        }
        findViewById<Button>(R.id.instructor_profile_deleteaccount).setOnClickListener {
            val progressBar = Dialog(this)
            progressBar.setContentView(R.layout.progress_bar)
            progressBar.show()
            FirebaseFirestore.getInstance().collection("Users")
                .document(FirebaseAuth.getInstance().currentUser.uid).delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        FirebaseAuth.getInstance().currentUser.delete()
                            .addOnCompleteListener { task2 ->
                                if (task2.isSuccessful) {
                                    progressBar.hide()
                                    Toast.makeText(
                                        this@InstructorProfileActivity,
                                        "Your account and data has been deleted",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    startActivity(
                                        Intent(
                                            this@InstructorProfileActivity,
                                            LoginPage::class.java
                                        )
                                    )
                                    finish()
                                } else {
                                    progressBar.hide()
                                    Toast.makeText(
                                        this@InstructorProfileActivity,
                                        "An error Occurred",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    } else {
                        Toast.makeText(
                            this@InstructorProfileActivity,
                            "An error Occured",
                            Toast.LENGTH_SHORT
                        ).show()
                        progressBar.hide()

                    }
                }
        }
        findViewById<Button>(R.id.instructor_profile_editprofile).setOnClickListener {
            startActivity(
                Intent(
                    this@InstructorProfileActivity,
                    InstructorEditProfileActivity::class.java
                )
            )
        }
        val name_tv = findViewById<TextView>(R.id.instructor_profile_name_tv)
        val email_tv = findViewById<TextView>(R.id.instructor_profile_emailid_tv)
        val degree_tv = findViewById<TextView>(R.id.instructor_profile_degree_tv)
        val job_tv = findViewById<TextView>(R.id.instructor_profile_currentjob_tv)
        val special_tv = findViewById<TextView>(R.id.instructor_profile_areaofspecialisation_tv)
        FirebaseFirestore.getInstance().collection("Users")
            .document(FirebaseAuth.getInstance().currentUser.uid).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot != null) {
                    val data = documentSnapshot.data
                    name_tv.text = data!!.get("Name").toString()
                    email_tv.text = FirebaseAuth.getInstance().currentUser.email
                    degree_tv.text = data!!.get("Degree").toString()
                    job_tv.text = data!!.get("Job").toString()
                    special_tv.text = data!!.get("Specialisation").toString()

                }
            }
    }
}