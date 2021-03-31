package com.example.insights

import BookFragment
import HomeFragment
import ProfileFragment
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class mainPageNew : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page_new)

        val homeFragment = HomeFragment()
        val bookFragment = BookFragment()
        val profileFragment = ProfileFragment()

        setCurrentFragment(homeFragment)


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        findViewById<ImageButton>(R.id.main_profile_ic).setOnClickListener {
            val db = FirebaseFirestore.getInstance().collection("Users")
                .document(FirebaseAuth.getInstance().currentUser.uid)
            db.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val hashMapData = document.data
                        val type = hashMapData?.get("type").toString()
                        if (type == "Student") {
                            startActivity(Intent(this@mainPageNew, student_profile::class.java))
                        } else {
                            startActivity(
                                Intent(
                                    this@mainPageNew,
                                    InstructorProfileActivity::class.java
                                )
                            )
                        }
                    }
                }
        }
        findViewById<ImageButton>(R.id.forum_start_btn).setOnClickListener {
            startActivity(Intent(this@mainPageNew, ForumnActivity::class.java))
        }


        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.book -> setCurrentFragment(bookFragment)


            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    fun signOutUser(view: View) {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this@mainPageNew, LoginPage::class.java))
        Toast.makeText(this@mainPageNew,"Signned Out SuccessFully",Toast.LENGTH_SHORT).show()
        finish()
    }

}


