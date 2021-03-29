package com.example.insights

import BookFragment
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.net.URL

class ItemAdapter(val context: BookFragment, val items: ArrayList<HashMap<String,String>>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    lateinit var url: String
    lateinit var uri: Uri
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.home_custom_row,parent,false))
    }
    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        val item=items.get(position)
        holder.topicItem.text=item["Book Name"]
        url = item["PdfUrl"].toString()
        val dataReference = FirebaseStorage.getInstance().getReferenceFromUrl(url)
        val localFile = File.createTempFile("Book","Pdf")
        uri = Uri.fromFile(localFile)
        dataReference.getFile(localFile).addOnSuccessListener {

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        lateinit var uriImage: Uri
        lateinit var uriPdf: Uri
        val topicItem: TextView =view.findViewById(R.id.topic_name)
        val cardViewItem: CardView =view.findViewById(R.id.card_view_item)
        init {
            view.setOnClickListener { v:View->
                val position:Int=adapterPosition
                val intent= Intent(Intent.ACTION_VIEW)
                intent.setType("application/pdf")

                intent.setData(uri)



            }
        }

    }


}


