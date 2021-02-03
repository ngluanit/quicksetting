package com.example.settingapp.colors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.settingapp.MainActivity
import com.example.settingapp.R
import kotlinx.android.synthetic.main.activity_colors.*

class ColorsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colors)
        imgBack.setOnClickListener {
            val intent = Intent(this,com.example.miui_ify.MainActivity::class.java);
            startActivity(intent)
        }
    }
}