package com.example.insights

import HomeFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(val context: HomeFragment, val items: ArrayList<HashMap<String,String>>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.home_custom_row,parent,false))
    }

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        val item=items.get(position)
        holder.topicItem.text=item["Title"]
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val topicItem: TextView =view.findViewById(R.id.topic_name)
        val cardViewItem: CardView =view.findViewById(R.id.card_view_item)

    }
}