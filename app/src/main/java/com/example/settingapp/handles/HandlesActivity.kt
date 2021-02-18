package com.example.settingapp.handles

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.miui_ify.MainActivity
import com.example.settingapp.R
import kotlinx.android.synthetic.main.activity_colors.*
import kotlinx.android.synthetic.main.activity_colors.imgBack
import kotlinx.android.synthetic.main.activity_handles.*
import kotlinx.android.synthetic.main.activity_sliders.*
import kotlinx.android.synthetic.main.layout_handle_seekbar.*

class HandlesActivity : AppCompatActivity() {
    private var imgTurnFullleghtShown = true
    private var imgTurnHideHandleShown = true
    private var imgTurnHideFullScreenShown = true
    private var imgTurnHidelandscapeShown = true
    private var imgTurnHideKeyboardOpenShown = true
    private var imgTurnDisableHandleShown = true
    private var imgTurnDisableleftHandleShown = true
    private var imgTurnDisableRightHandleShown = true

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handles)
        val window = window
        getWindow().decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark

//        getWindow().statusBarColor = Color.parseColor("#fff") // set status background white

        imgTurnFullleght.setOnClickListener {
            if ((imgTurnFullleght != null) && (imgTurnFullleghtShown)){
                imgTurnFullleght.setImageResource(R.drawable.ic_switch_on);
                imgTurnFullleghtShown = false
            }
            else{
                if (imgTurnFullleght != null) imgTurnFullleght.setImageResource(R.drawable.ic_switch_off);
                imgTurnFullleghtShown = true
            }
        }

        imgTurnHideHandle.setOnClickListener {
            if ((imgTurnHideHandle != null) && (imgTurnHideHandleShown)){
                imgTurnHideHandle.setImageResource(R.drawable.ic_switch_on);
                imgTurnHideHandleShown = false
            }
            else{
                if (imgTurnHideHandle != null) imgTurnHideHandle.setImageResource(R.drawable.ic_switch_off);
                imgTurnHideHandleShown = true
            }
        }

        imgTurnHideFullScreen.setOnClickListener {
            if ((imgTurnHideFullScreen != null) && (imgTurnHideFullScreenShown)){
                imgTurnHideFullScreen.setImageResource(R.drawable.ic_switch_on);
                imgTurnHideFullScreenShown = false
            }
            else{
                if (imgTurnHideFullScreen != null) imgTurnHideFullScreen.setImageResource(R.drawable.ic_switch_off);
                imgTurnHideFullScreenShown = true
            }
        }

        imgTurnHidelandscape.setOnClickListener {
            if ((imgTurnHidelandscape != null) && (imgTurnHidelandscapeShown)){
                imgTurnHidelandscape.setImageResource(R.drawable.ic_switch_on);
                imgTurnHidelandscapeShown = false
            }
            else{
                if (imgTurnHidelandscape != null) imgTurnHidelandscape.setImageResource(R.drawable.ic_switch_off);
                imgTurnHidelandscapeShown = true
            }
        }

        imgTurnHideKeyboardOpen.setOnClickListener {
            if ((imgTurnHideKeyboardOpen != null) && (imgTurnHideKeyboardOpenShown)){
                imgTurnHideKeyboardOpen.setImageResource(R.drawable.ic_switch_on);
                imgTurnHideKeyboardOpenShown = false
            }
            else{
                if (imgTurnHideKeyboardOpen != null) imgTurnHideKeyboardOpen.setImageResource(R.drawable.ic_switch_off);
                imgTurnHideKeyboardOpenShown = true
            }
        }

        imgTurnDisableHandle.setOnClickListener {
            if ((imgTurnDisableHandle != null) && (imgTurnDisableHandleShown)){
                imgTurnDisableHandle.setImageResource(R.drawable.ic_switch_on);
                imgTurnDisableHandleShown = false
            }
            else{
                if (imgTurnDisableHandle != null) imgTurnDisableHandle.setImageResource(R.drawable.ic_switch_off);
                imgTurnDisableHandleShown = true
            }
        }

        imgTurnDisableleftHandle.setOnClickListener {
            if ((imgTurnDisableleftHandle != null) && (imgTurnDisableleftHandleShown)){
                imgTurnDisableleftHandle.setImageResource(R.drawable.ic_switch_on);
                imgTurnDisableleftHandleShown = false
            }
            else{
                if (imgTurnDisableleftHandle != null) imgTurnDisableleftHandle.setImageResource(R.drawable.ic_switch_off);
                imgTurnDisableleftHandleShown = true
            }
        }

        imgTurnDisableRightHandle.setOnClickListener {
            if ((imgTurnDisableRightHandle != null) && (imgTurnDisableRightHandleShown)){
                imgTurnDisableRightHandle.setImageResource(R.drawable.ic_switch_on);
                imgTurnDisableRightHandleShown = false
            }
            else{
                if (imgTurnDisableRightHandle != null) imgTurnDisableRightHandle.setImageResource(R.drawable.ic_switch_off);
                imgTurnDisableRightHandleShown = true
            }
        }

        imgBack.setOnClickListener {
          onBackPressed()
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java);
        startActivity(intent)
        finish()
    }
}