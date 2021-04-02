package com.example.insights

import BookFragment
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(val context: BookFragment, val items: ArrayList<HashMap<String, String>>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    lateinit var url: String
    lateinit var uri: Uri

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.home_custom_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        val item = items.get(position)
        holder.topicItem.text = item["Book Name"]

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


    }


}






