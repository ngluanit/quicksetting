package com.example.settingapp.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.settingapp.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {
    var viewPagerAdapter : ViewPagerAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        viewPagerAdapter=ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter=viewPagerAdapter
    }
}