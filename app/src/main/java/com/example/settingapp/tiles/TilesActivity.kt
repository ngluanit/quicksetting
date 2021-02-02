package com.example.settingapp.tiles

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.settingapp.R
import com.example.settingapp.dialog.TileToggleDialog
import kotlinx.android.synthetic.main.activity_tiles.*
import kotlinx.android.synthetic.main.dialog_tile_toggle.*
import kotlinx.android.synthetic.main.dialog_tile_toggle.btn_cancel


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
        rlTiletoggle.setOnClickListener {
            showDialog(this)
        }
        rlTileoption.setOnClickListener {
            startActivity(Intent(this,TileOptionsActivity::class.java))
        }
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

    private fun showDialog1(tilesActivity: TilesActivity) {
        val dialog = Dialog(tilesActivity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_img_tiles)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.btn_cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }

    fun showDialog(activity: Activity?) {
        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_tile_toggle)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.rlNormal.setOnClickListener {
            Toast.makeText(activity,"OKOKO",Toast.LENGTH_LONG).show()
        }
        dialog.btn_cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
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