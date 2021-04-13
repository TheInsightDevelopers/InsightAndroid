package com.example.insights.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.insights.R


class forumadapter(val context: Context, val items: ArrayList<HashMap<String, String>>) :
    RecyclerView.Adapter<forumadapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.forum_custom_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        holder.tv_item.text = item["message"]
        holder.sender.text = item["sender"]+"(" + item["type"]+")"
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_item = view.findViewById<TextView>(R.id.forum_message)

        val sender = view.findViewById<TextView>(R.id.forum_sender_tv)
    }

}