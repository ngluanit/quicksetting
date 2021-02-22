package com.example.settingapp.tilestyle

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miui_ify.MainActivity
import com.example.settingapp.R
import com.example.settingapp.tiles.TilesActivity
import kotlinx.android.synthetic.main.activity_colors.imgBack
import kotlinx.android.synthetic.main.activity_tile_styles.*
import kotlinx.android.synthetic.main.dialog_tile_toggle.*

class TileStylesActivity : AppCompatActivity() {
    private var img_enableShown = true

    @SuppressLint("WrongConstant")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tile_styles)
        val window: Window = this.getWindow()
        getWindow().decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark

        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        img_enable.setOnClickListener {
            if ((img_enable != null) && (img_enableShown)){
                img_enable.setImageResource(R.drawable.ic_switch_on);
                img_enableShown = false
            }
            else{
                if (img_enable != null) img_enable.setImageResource(R.drawable.ic_switch_off);
                img_enableShown = true
            }
        }

        rlShape.setOnClickListener {
            showDialog(this)
        }
        imgBack.setOnClickListener {
            onBackPressed()
        }


        val posts = listOf(R.drawable.ic_gradient1,R.drawable.ic_gradient2,R.drawable.ic_gradient3,R.drawable.ic_gradient3,R.drawable.ic_gradient4,R.drawable.ic_gradient5,R.drawable.ic_gradient1,R.drawable.ic_gradient1,R.drawable.ic_gradient1)
        rcvColor.apply {
            layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
           // adapter = TileStyleAdapter(posts)
            rcvColor.adapter=TileStyleAdapter(posts)
        }
        rcvColor2.apply {
            layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
            adapter = TileStyleAdapter(posts)
        }
    }

    private fun showDialog(tileStylesActivity: TileStylesActivity) {
        val dialog = Dialog(tileStylesActivity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_layout_circle)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.btn_cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }


    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java);
        startActivity(intent)
        finish()
    }
}
