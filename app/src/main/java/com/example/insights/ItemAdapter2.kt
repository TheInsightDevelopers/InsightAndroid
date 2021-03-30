package com.example.insights

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter2(val context: Context, val items :ArrayList<String>): RecyclerView.Adapter<ItemAdapter2.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter2.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_custom_row_home_video,parent,false))
    }

    override fun onBindViewHolder(holder: ItemAdapter2.ViewHolder, position: Int) {
        val item=items.get(position)
        holder.videoName.text=item
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val videoName: TextView =view.findViewById(R.id.video_name)
        val cardViewItem: CardView =view.findViewById(R.id.card_view_item_home)

    }
}
