package com.example.ladifinal

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter(private val links : List<PictureLink>,private val context: Context)
    : RecyclerView.Adapter<Adapter.ViewHolder>(){

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.imageView)
        val userName : TextView = itemView.findViewById(R.id.userNameText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.picture_info, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = links.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = links[position]
        holder.userName.text = p.name
        Glide.with(context)
            .load(p.link)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.image)

    }
}