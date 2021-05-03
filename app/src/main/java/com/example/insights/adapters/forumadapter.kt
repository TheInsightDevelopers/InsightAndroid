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
        holder.sender.text = item["sender"] + "(" + item["type"] + ")"
        val time = item["time"].toString()
        val month = time.substring(4, 6)
        val date = time.substring(6, 8)
        val hour = time.substring(8, 10)
        val minute = time.substring(10, 12)
        holder.timeStamp.text = hour + ":" + minute + "  " + date + "-" + month
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_item = view.findViewById<TextView>(R.id.forum_message)

        val sender = view.findViewById<TextView>(R.id.forum_sender_tv)

        val timeStamp: TextView = view.findViewById(R.id.chat_time_stamp)
    }

}