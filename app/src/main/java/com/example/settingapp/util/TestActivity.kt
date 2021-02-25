package com.example.settingapp.util

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.settingapp.R

class TestActivity : AppCompatActivity() {
    var textView: TextView? = null
    var sharedPreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        textView = findViewById(R.id.textView)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val counter: Int = sharedPreferences!!.getInt("counter", 0)
        textView!!.setText("You have opened the quick settings $counter times.")
    }

    override fun onResume() {
        super.onResume()
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val counter: Int = sharedPreferences!!.getInt("counter", 0)
        textView!!.setText("You have opened the quick settings $counter times.")
    }
}