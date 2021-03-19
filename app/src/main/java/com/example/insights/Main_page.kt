package com.example.insights

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Loads [MainFragment].
 */
class Main_page : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        findViewById<Button>(R.id.profile_btn).setOnClickListener {
            val db = FirebaseFirestore.getInstance().collection("Users")
                .document(FirebaseAuth.getInstance().currentUser.uid)
            db.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val hashMapData = document.data
                        val type = hashMapData?.get("type").toString()
                        if (type == "Student") {
                            startActivity(Intent(this@Main_page, student_profile::class.java))
                        }
                    }
                }
        }
    }
}