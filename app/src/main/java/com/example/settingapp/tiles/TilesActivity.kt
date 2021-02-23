package com.example.settingapp.tiles

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.settingapp.R
import kotlinx.android.synthetic.main.activity_tile_styles.*
import kotlinx.android.synthetic.main.activity_tiles.*
import kotlinx.android.synthetic.main.activity_tiles.imgBack
import kotlinx.android.synthetic.main.dialog_tile_toggle.*


class TilesActivity : AppCompatActivity(),IconNotiAdapter.ItemClick,IconActiveNotiAdapter.ItemClick {
    var list:MutableList<ItemNotification>?=null
    var list1:MutableList<ItemNotification>?=null
    var iconNotiAdapter:IconNotiAdapter?=null
    var iconNotiAdapter1:IconActiveNotiAdapter?=null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiles)

        val window: Window = this.getWindow()
        getWindow().decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark

        window.statusBarColor = ContextCompat.getColor(this, R.color.statusbar)


        imgBack.setOnClickListener {
            val intent = Intent(this, com.example.miui_ify.MainActivity::class.java);
            startActivity(intent)
        }
        list=ArrayList()
        list1=ArrayList()
        rlTiletoggle.setOnClickListener {
            showDialog(this)
        }
        rlTileoption.setOnClickListener {
            startActivity(Intent(this, TileOptionsActivity::class.java))
        }
        rlSelectIcon.setOnClickListener {
            showDialog1(this)
        }
        rl_Url.setOnClickListener {
            showDialogURL(this)
        }
        rl_app.setOnClickListener {
            showDialogApp(this)
        }
        val posts = listOf(
            R.drawable.ic_wifi_icon,
            R.drawable.ic_mobile_data,
            R.drawable.ic_bluetooth_activity,
            R.drawable.ic_sync,
            R.drawable.ic_location_icon,
            R.drawable.ic_auto_rotate,
            R.drawable.ic_minus_circle_outline,
            R.drawable.ic_torch_icon,
            R.drawable.ic_nfc
        )
        val text1= listOf(
            "Wi-Fi",
            "Mobile data",
            "Bluetooth",
            "Sync",
            "Location",
            "Auto-rotate",
            "Do not disturb",
            "Torch",
            "NFC"
        )
        rcvInActive.setLayoutManager(GridLayoutManager(this, 4))
        rcvActive.setLayoutManager(GridLayoutManager(this, 4))
        for (i in 0..7){
            var itemNotification:ItemNotification
            itemNotification=ItemNotification()
            itemNotification.img= posts[i]
            itemNotification.name= text1[i]
            list!!.add(itemNotification)
        }
        iconNotiAdapter=IconNotiAdapter(list, this)
        iconNotiAdapter1= IconActiveNotiAdapter(list1, this)
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
    private fun showDialogURL(tilesActivity: TilesActivity) {
        val dialog = Dialog(tilesActivity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_url)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.btn_cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }
    private fun showDialogApp(tilesActivity: TilesActivity) {
        val dialog = Dialog(tilesActivity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_app_tiles)
        val rcv_app: RecyclerView = dialog.findViewById<View>(R.id.rcv_app) as RecyclerView
        var adapter: ItemAppAdapter
        var itemarraylist: ArrayList<ItemApp> = ArrayList()
        adapter = ItemAppAdapter(this, ArrayList(itemarraylist))
        itemarraylist.add(ItemApp("album", R.drawable.ic_gradient1))
        itemarraylist.add(ItemApp("e an com chua", R.drawable.ic_gradient1))
        itemarraylist.add(ItemApp("ahihi", R.drawable.ic_gradient1))
        itemarraylist.add(ItemApp("aaaaa", R.drawable.ic_gradient1))
        itemarraylist.add(ItemApp("bbbbbbbb", R.drawable.ic_gradient1))
        rcv_app.adapter = adapter;
        adapter!!.notifyDataSetChanged()
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
            Toast.makeText(activity, "OKOKO", Toast.LENGTH_LONG).show()
        }
        dialog.btn_cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
    override fun onItemclick(position: Int) {
        list1!!.add(list!![position])
        list!!.remove(list!![position])
        iconNotiAdapter=IconNotiAdapter(list, this)
        iconNotiAdapter1= IconActiveNotiAdapter(list1, this)
        rcvInActive.adapter=iconNotiAdapter
        rcvActive.adapter=iconNotiAdapter1
        iconNotiAdapter1!!.notifyDataSetChanged()
        iconNotiAdapter!!.notifyDataSetChanged()
    }

    override fun onItemclick1(position: Int) {
        list!!.add(list1!![position])
        list1!!.remove(list1!![position])
        iconNotiAdapter=IconNotiAdapter(list, this)
        iconNotiAdapter1= IconActiveNotiAdapter(list1, this)
        rcvInActive.adapter=iconNotiAdapter
        rcvActive.adapter=iconNotiAdapter1
        iconNotiAdapter1!!.notifyDataSetChanged()
        iconNotiAdapter!!.notifyDataSetChanged()
    }
}


