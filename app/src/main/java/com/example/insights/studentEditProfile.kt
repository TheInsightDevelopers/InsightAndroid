package com.example.insights

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class studentEditProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_edit_profile)
        findViewById<Button>(R.id.stud_profile_update_btn).setOnClickListener {
            //getting the input
            val name = findViewById<EditText>(R.id.et_edit_profile_stud_name).text.toString()
            val fav = findViewById<EditText>(R.id.et_edit_profile_stud_fav_subject).text.toString()
            val classname = findViewById<EditText>(R.id.et_edit_profile_stud_class).text.toString()
            val school = findViewById<EditText>(R.id.et_edit_profile_stud_institute).text.toString()
            val data = hashMapOf("Name" to name,
                                    "Class" to classname,
                                        "Fav_subject" to fav,
                                            "School" to school)
            FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().currentUser.uid).set(data).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this@studentEditProfile,"Your Profile Has Been Updated",Toast.LENGTH_SHORT).show()
                    finish()
                }
                else{
                    Toast.makeText(this@studentEditProfile,"An error Occurred",Toast.LENGTH_SHORT).show()

                }
            }

        }



    }
}


