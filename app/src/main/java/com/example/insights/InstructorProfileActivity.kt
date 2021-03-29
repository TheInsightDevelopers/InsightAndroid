package com.example.insights

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class InstructorProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor_profile_page)
        val name_tv = findViewById<TextView>(R.id.instructor_profile_name_tv)
        val email_tv = findViewById<TextView>(R.id.instructor_profile_emailid_tv)
        val degree_tv = findViewById<TextView>(R.id.instructor_profile_degree_tv)
        val job_tv = findViewById<TextView>(R.id.instructor_profile_currentjob_tv)
        val special_tv = findViewById<TextView>(R.id.instructor_profile_areaofspecialisation_tv)
        FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().currentUser.uid).get().addOnSuccessListener { documentSnapshot ->
            if(documentSnapshot != null){
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