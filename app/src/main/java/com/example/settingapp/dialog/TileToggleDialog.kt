package com.example.settingapp.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.core.content.ContextCompat.startActivity
import com.example.miui_ify.MainActivity
import com.example.settingapp.R
import kotlinx.android.synthetic.main.activity_colors.*

class TileToggleDialog(context: Context) : Dialog(context), View.OnClickListener{
    var activity:Activity?=null
    var dialog:Dialog?=null
    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_tile_toggle)

    }
}