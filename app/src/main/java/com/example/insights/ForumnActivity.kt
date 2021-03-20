package com.example.insights

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.*

class ForumnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forumn)
        /***************************************Sending Message***********************************************/
        //adding onclick listener
        findViewById<ImageButton>(R.id.forum_send_button).setOnClickListener {
            //getting message
            var message = findViewById<EditText>(R.id.message_input).text.toString()
            if(message.isNotEmpty()){
                var current_time = Date().time.toString()
                var data = hashMapOf("message" to message,
                                        "time" to current_time,
                                            "sender" to FirebaseAuth.getInstance().currentUser.email)
                // Sending Message to Firestore
                FirebaseFirestore.getInstance().collection("message").document(current_time).set(data)

            }
            findViewById<EditText>(R.id.message_input).text.clear()
        }
    }
    /***************************************Listener Message***********************************************/
    

}