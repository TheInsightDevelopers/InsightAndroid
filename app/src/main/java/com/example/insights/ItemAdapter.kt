package com.example.insights

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

class BookRecyclerViewAdapter(val context: BookFragment, val items: ArrayList<HashMap<String, String>>) :
    RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder>() {
    lateinit var url: String
    lateinit var uri: Uri

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookRecyclerViewAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.home_custom_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookRecyclerViewAdapter.ViewHolder, position: Int) {
        val item = items.get(position)
        holder.topicItem.text = item["Book Name"]
        val image_url = item["ImageUrl"].toString()
        Glide.with(holder.bookImage)
            .load(image_url)
            .centerCrop()
            .placeholder(R.drawable.send_ic)
            .error(R.mipmap.ic_launcher)
            .fallback(R.mipmap.ic_launcher).into(holder.bookImage)

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
        val bookImage: ImageView = view.findViewById(R.id.book_image_view)


    }


}






