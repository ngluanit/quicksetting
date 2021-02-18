package com.example.settingapp.tilestyle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.settingapp.R

class TileStyleAdapter(val posts: List<Int>) : RecyclerView.Adapter<TileStyleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TileStyleAdapter.ViewHolder {
       val view : View = LayoutInflater.from(parent.context).inflate(R.layout.layout_gradient,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TileStyleAdapter.ViewHolder, position: Int) {
        holder.img_gradient.imageAlpha = posts[position]
    }

    override fun getItemCount() = posts.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img_gradient: ImageView = itemView.findViewById(R.id.img_gradient)
    }

}