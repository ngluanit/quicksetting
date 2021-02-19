package com.example.miui_ify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.settingapp.R
import com.example.settingapp.tiles.ItemNotification

public class IconSettingAdapter(private var list: List<ItemNotification>?) :
    RecyclerView.Adapter<IconSettingAdapter.MyviewHolder>() {
    lateinit var itemClick : ItemClick
    inner class MyviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgIcon: ImageView? = null
        var tvIcon: TextView? = null
        init {
            imgIcon = itemView.findViewById(R.id.icon)
            tvIcon = itemView.findViewById(R.id.tvIcon)
        }
    }
    fun setIClick(itemClick1: ItemClick){
        itemClick=itemClick1;
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_icon, parent, false)
        return MyviewHolder(view)
    }
    override fun getItemCount(): Int {
        return list!!.size
    }
    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val myviewHolder = holder
        myviewHolder.tvIcon!!.text = list!!.get(position).name
        myviewHolder.imgIcon!!.setImageResource(list!!.get(position).img!!)
        myviewHolder.itemView.setOnClickListener {
            itemClick.onItemclick1(position)
        }
    }
    interface ItemClick{
        fun onItemclick1(position: Int)
    }
}