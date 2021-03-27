package com.example.insights

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.insights.adapters.forumadapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ForumnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forumn)
        val db = FirebaseFirestore.getInstance()
        //Initalize message data
        val messagedata = ArrayList<HashMap<String,String>>()
        /*****************************************************Recycler View*****************************************************/
        val recyclerview = findViewById<RecyclerView>(R.id.chat_recycler)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val Itemadapter = forumadapter(this, messagedata)
        recyclerview.adapter = Itemadapter
        recyclerview.scrollToPosition((Itemadapter.getItemCount())-1)



        /***************************************Sending Message***********************************************/
        //adding onclick listener
        findViewById<ImageButton>(R.id.forum_send_button).setOnClickListener {
            //getting message
            val currentUser = FirebaseAuth.getInstance().currentUser
            db.collection("Users").document(currentUser.uid).get()
                .addOnSuccessListener { values ->
                    //getting message
                    if (values != null) {
                        val userdata = (values.data)
                        val name = userdata?.get("Name")
                        var message = findViewById<EditText>(R.id.message_input).text.toString()
                        if (message.isNotEmpty()) {
                            var current_time = Date().time.toString()
                            var data = hashMapOf(
                                "message" to message,
                                "time" to current_time,
                                "sender" to name,
                                "uid" to currentUser.uid
                            )
                            // Sending Message to Firestore
                            db.collection("message")
                                .document(current_time).set(data)

                        }
                    }

                    findViewById<EditText>(R.id.message_input).text.clear()
                }
        }

        /****************************************Firebase Listener************************************************/
        db.collection("message").addSnapshotListener { value, error ->
            if(error== null){
                if (value != null) {
                    for (change in value!!.documentChanges){
                       if(change.type == DocumentChange.Type.ADDED){
                           messagedata.add(change.document.data as HashMap<String, String>)
                           val Itemadapter = forumadapter(this, messagedata)
                           recyclerview.adapter = Itemadapter
                           recyclerview.scrollToPosition((Itemadapter.getItemCount())-1)
                       }
                    }
                }
            }
        }

    }



}
