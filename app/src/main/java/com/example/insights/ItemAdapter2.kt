package com.example.insights

import HomeFragment
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter2(val context: HomeFragment, val items :ArrayList<HashMap<String,String>>): RecyclerView.Adapter<ItemAdapter2.ViewHolder>() {
    lateinit var Url: String
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter2.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_custom_row_home_video,parent,false))
    }

    override fun onBindViewHolder(holder: ItemAdapter2.ViewHolder, position: Int) {
        val item=items.get(position)
        holder.videoName.text=item["Title"]
        holder.itemView.setOnClickListener(object : View.OnClickListener {

            override fun onClick(view: View) {
                Url = item["VideoUrl"].toString()
                val intent = Intent(Intent.ACTION_VIEW)
                intent.type = "application/video"
                intent.data = Uri.parse(Url)
                ContextCompat.startActivity(
                    view.context,
                    Intent.createChooser(intent, "Open Video"),
                    Bundle.EMPTY
                )


            }
        })
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val videoName: TextView =view.findViewById(R.id.video_name)
        val cardViewItem: CardView =view.findViewById(R.id.card_view_item_home)

    }
}
