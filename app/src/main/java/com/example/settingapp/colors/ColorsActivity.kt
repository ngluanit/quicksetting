package com.example.settingapp.colors

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.settingapp.MainActivity
import com.example.settingapp.R
import kotlinx.android.synthetic.main.activity_colors.*

class ColorsActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colors)
        val window: Window = this.getWindow()
        getWindow().decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        imgBack.setOnClickListener {
            val intent = Intent(this,com.example.miui_ify.MainActivity::class.java);
            startActivity(intent)
        }
    }
}