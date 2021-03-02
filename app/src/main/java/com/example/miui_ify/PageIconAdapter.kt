package com.example.miui_ify

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.settingapp.R
import com.example.settingapp.tiles.ItemNotification
import com.example.settingapp.util.SharePref

class PageIconAdapter(private var list: List<List<ItemNotification>>, private var context: Context):
    RecyclerView.Adapter<PageIconAdapter.Pageviewholder>() {
    inner class Pageviewholder(itemView:View):RecyclerView.ViewHolder(itemView){
        var rcvPage:RecyclerView?=null
        init {
            rcvPage=itemView.findViewById(R.id.rcvIconPage)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pageviewholder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_page_icon,parent,false)
        return Pageviewholder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: Pageviewholder, position: Int) {
        holder.rcvPage!!.layoutManager=GridLayoutManager(context,SharePref.getIntPref(context,"number_column"))
        val iconSettingAdapter = IconSettingAdapter(list.get(position),context)
        holder.rcvPage!!.setAdapter(iconSettingAdapter)
    }

}