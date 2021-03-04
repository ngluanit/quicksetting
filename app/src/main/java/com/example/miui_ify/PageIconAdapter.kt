package com.example.miui_ify

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.settingapp.R
import com.example.settingapp.tiles.ItemNotification
import com.example.settingapp.util.SharePref

class PageIconAdapter(private var list: List<List<ItemNotification>>, private var context: Context):
    RecyclerView.Adapter<PageIconAdapter.Pageviewholder>(),IconSettingAdapter.ItemClick {
    lateinit var itemInPageClick : ItemInPageClick
    fun setIClick(itemInPageClick1: ItemInPageClick){
        itemInPageClick=itemInPageClick1;
    }
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
        iconSettingAdapter.setIClick(this);
        holder.rcvPage!!.setAdapter(iconSettingAdapter)
//        holder.itemView.setOnClickListener {
//            itemInPageClick.onIteminPageclick1(position)
//        }
        holder.itemView.setOnClickListener {
            println("123213??"+position)
        }
    }

    override fun onItemclick1(position: Int) {
        itemInPageClick.onIteminPageclick1(position)
    }
    interface ItemInPageClick{
        fun onIteminPageclick1(position: Int)
    }
}