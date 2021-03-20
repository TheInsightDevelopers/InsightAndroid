package com.example.insights

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class studentEditProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_edit_profile)
        FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().currentUser.email).get().addOnSuccessListener {
            values ->
            if(values != null){
                var data = values.data
                var name = data?.get("Name").toString()
                var fav_subject = data?.get("Fav_subject").toString()
                var classname = data?.get("Class").toString()
                var school = data?.get("School").toString()
                val et_name = findViewById<EditText>(R.id.et_edit_profile_stud_name)
                val et_school = findViewById<EditText>(R.id.et_edit_profile_stud_institute)
                val et_fav = findViewById<EditText>(R.id.et_edit_profile_stud_fav_subject)
                val et_class = findViewById<EditText>(R.id.et_edit_profile_stud_class)
                //Fixing Default values
                et_name.setText(name)
                et_school.setText(school)
                et_fav.setText(fav_subject)
                et_class.setText(classname)


            }

        }


    }
}