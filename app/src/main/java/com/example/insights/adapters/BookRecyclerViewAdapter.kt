package com.example.insights.adapters

import BookFragment
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.insights.R
import com.example.insights.mainPageNew
import com.google.firebase.storage.FirebaseStorage

class BookRecyclerViewAdapter(val context: BookFragment, val items: ArrayList<HashMap<String, String>>) :
    RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder>() {
    lateinit var url: String
    lateinit var uri: Uri

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.home_custom_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        holder.topicItem.text = item["Book Name"]
        val imageUrl = item["ImageUrl"]
        val imageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl.toString())
        Glide.with(holder.bookImage.context).load(imageReference).into(holder.bookImage)

        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(Intent.ACTION_VIEW)
                url = item["PdfUrl"].toString()
                intent.type = "application/pdf"
                intent.data = Uri.parse(url)
                startActivity(view.context, Intent.createChooser(intent, "Open PDF"), Bundle.EMPTY)


            }
        })

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var uriImage: Uri
        lateinit var uriPdf: Uri
        val topicItem: TextView = view.findViewById(R.id.topic_name)
        val cardViewItem: CardView = view.findViewById(R.id.card_view_item)
        val bookImage: ImageView = view.findViewById(R.id.book_image)

    }


}






