package com.example.settingapp.tilestyle

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miui_ify.MainActivity
import com.example.settingapp.R
import kotlinx.android.synthetic.main.activity_colors.imgBack
import kotlinx.android.synthetic.main.activity_tile_styles.*

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

        imgBack.setOnClickListener {
            onBackPressed()
        }

        val posts = listOf(R.drawable.ic_gradient1,R.drawable.ic_gradient2,R.drawable.ic_gradient3,R.drawable.ic_gradient3,R.drawable.ic_gradient4,R.drawable.ic_gradient5,R.drawable.ic_gradient1,R.drawable.ic_gradient1,R.drawable.ic_gradient1)
        rcvColor.apply {
            layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
            adapter = TileStyleAdapter(posts)
        }
        rcvColor2.apply {
            layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
            adapter = TileStyleAdapter(posts)
        }
    }


    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java);
        startActivity(intent)
        finish()
    }
}
