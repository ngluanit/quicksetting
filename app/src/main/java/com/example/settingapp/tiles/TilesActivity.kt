package com.example.settingapp.tiles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.settingapp.R
import kotlinx.android.synthetic.main.activity_tiles.*


class TilesActivity : AppCompatActivity(),IconNotiAdapter.ItemClick,IconActiveNotiAdapter.ItemClick {
    var list:MutableList<ItemNotification>?=null
    var list1:MutableList<ItemNotification>?=null
    var iconNotiAdapter:IconNotiAdapter?=null
    var iconNotiAdapter1:IconActiveNotiAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiles)
        list=ArrayList()
        list1=ArrayList()
        rcvInActive.setLayoutManager(GridLayoutManager(this, 4))
        rcvActive.setLayoutManager(GridLayoutManager(this, 4))
        for (i in 0..12){
            var itemNotification:ItemNotification
            itemNotification=ItemNotification()
            itemNotification.img=R.drawable.ic_launcher_background
            itemNotification.name="test"
            list!!.add(itemNotification)
        }
        iconNotiAdapter=IconNotiAdapter(list,this)
        iconNotiAdapter1= IconActiveNotiAdapter(list1,this)
        rcvInActive.adapter=iconNotiAdapter
        rcvActive.adapter=iconNotiAdapter1

    }

    override fun onItemclick(position: Int) {
        list1!!.add(list!![position])
        list!!.remove(list!![position])
        iconNotiAdapter=IconNotiAdapter(list,this)
        iconNotiAdapter1= IconActiveNotiAdapter(list1,this)
        rcvInActive.adapter=iconNotiAdapter
        rcvActive.adapter=iconNotiAdapter1
        iconNotiAdapter1!!.notifyDataSetChanged()
        iconNotiAdapter!!.notifyDataSetChanged()
    }

    override fun onItemclick1(position: Int) {
        list!!.add(list1!![position])
        list1!!.remove(list1!![position])
        iconNotiAdapter=IconNotiAdapter(list,this)
        iconNotiAdapter1= IconActiveNotiAdapter(list1,this)
        rcvInActive.adapter=iconNotiAdapter
        rcvActive.adapter=iconNotiAdapter1
        iconNotiAdapter1!!.notifyDataSetChanged()
        iconNotiAdapter!!.notifyDataSetChanged()
    }
}