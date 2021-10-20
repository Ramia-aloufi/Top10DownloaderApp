package com.example.top10downloaderapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.textcell.view.*

class MyAdapter(var item:MutableList<AppName>):RecyclerView.Adapter<MyAdapter.ItemViewHolder>() {
    class ItemViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.textcell,parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var items = item[position]
        holder.itemView.tv.text =items.name
    }

    override fun getItemCount(): Int = item.size
}