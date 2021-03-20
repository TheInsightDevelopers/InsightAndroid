package com.example.insights

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class student_profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_profile)
        val db =  FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().currentUser.uid)
        db.get()
            .addOnSuccessListener{ document ->
            if(document != null){
                val hashMapData = document.data
                val name = hashMapData?.get("Name")
                val classname = hashMapData?.get("Class")
                val favSubject = hashMapData?.get("Fav_subject")
                val school = hashMapData?.get("School")
                val email = FirebaseAuth.getInstance().currentUser.email
                findViewById<TextView>(R.id.student_profile_name).text = name as CharSequence?
                findViewById<TextView>(R.id.student_profile_email).text = email
                findViewById<TextView>(R.id.student_profile_class).text = classname as CharSequence?
                findViewById<TextView>(R.id.student_profile_fav_subject).text = favSubject as CharSequence?
                findViewById<TextView>(R.id.student_profile_school).text = school as CharSequence?


            }
        }
    }
}