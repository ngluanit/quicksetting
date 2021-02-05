package com.example.settingapp.welcome

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.miui_ify.MainActivity
import com.example.settingapp.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {
    var viewPagerAdapter : ViewPagerAdapter?=null
    var context:Context?=null
    var pref: SharedPreferences? =null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context=applicationContext
        pref=context!!.getSharedPreferences("MyPref", 0) // 0 - for private mode
        if (pref!!.getBoolean("first", false).equals(true)){
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)
            finish()
        }else{
            setContentView(R.layout.activity_welcome)
            viewPagerAdapter=ViewPagerAdapter(supportFragmentManager)
            viewPager.adapter=viewPagerAdapter
            img_view.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java);
                startActivity(intent)
                finish()
            }
        }

    }
}