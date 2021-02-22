package com.example.settingapp.tiles

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.settingapp.R
import kotlinx.android.synthetic.main.activity_tile_options.*
import kotlinx.android.synthetic.main.dialog_tile_timeout.*
import kotlinx.android.synthetic.main.dialog_tile_toggle.*

class TileOptionsActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tile_options)

        val window: Window = this.window
        getWindow().decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark

        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        rlScreen.setOnClickListener {
            showDialog(this)
        }
        rlNightmode.setOnClickListener {
            showDialog1(this)
        }

    }

    private fun showDialog1(tileOptionsActivity: TileOptionsActivity) {
        val dialog = Dialog(tileOptionsActivity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_nightmode)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.btn_cancel.setOnClickListener {
//            dialog.dismiss()
//        }
        dialog.show()

    }

    @Suppress("LocalVariableName")
    fun showDialog(activity: Activity?) {
        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_tile_timeout)
        val spinner_timeout: Spinner = dialog.findViewById<View>(R.id.spinner_timeout) as Spinner
        val spinner_second: Spinner = dialog.findViewById<View>(R.id.spinner_second) as Spinner
        val spinner_third: Spinner = dialog.findViewById<View>(R.id.spinner_third) as Spinner

        val items = arrayOf("15 seconds", "30 seconds", "1 minute","2 minutes","5 minutes","10 minutes","30 minutes")
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items)
        val adapter2: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items)
        val adapter3: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner_timeout.adapter = adapter
        spinner_second.adapter = adapter
        spinner_third.adapter = adapter
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.btn_cancel.setOnClickListener {
//            dialog.dismiss()
//        }
        dialog.show()
    }
}