package com.example.settingapp.sliders

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.miui_ify.MainActivity
import com.example.settingapp.R
import com.example.settingapp.util.SharePref
import kotlinx.android.synthetic.main.activity_colors.imgBack
import kotlinx.android.synthetic.main.activity_sliders.*

class SlidersActivity : AppCompatActivity() {
    private var imgTurnShown = true
    private var imgTurnMediaShown = true
    private var imgTurnAlarmShown = true
    private var imgTurnRingShown = true
    private var imgTurnNotiShown = true
    private var imgTurnVoiceShown = true

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sliders)
        val window: Window = this.getWindow()
        getWindow().decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark

        window.statusBarColor = ContextCompat.getColor(this, R.color.statusbar)
        if (SharePref.getBooleanPref(this,"brightbar")){
            imgTurn.setImageResource(R.drawable.ic_switch_on)
        }else{
            if (imgTurn != null) imgTurn.setImageResource(R.drawable.ic_switch_off)
        }
        if (SharePref.getBooleanPref(this,"mediabar")){
            imgTurnMedia.setImageResource(R.drawable.ic_switch_on)
        }else{
            if (imgTurnMedia != null) imgTurnMedia.setImageResource(R.drawable.ic_switch_off)
        }
        if (SharePref.getBooleanPref(this,"alarmbar")){
            imgTurnAlarm.setImageResource(R.drawable.ic_switch_on)
        }else{
            if (imgTurnAlarm != null) imgTurnAlarm.setImageResource(R.drawable.ic_switch_off)
        }
        if (SharePref.getBooleanPref(this,"ringbar")){
            imgTurnRing.setImageResource(R.drawable.ic_switch_on)
        }else{
            if (imgTurnRing != null) imgTurnRing.setImageResource(R.drawable.ic_switch_off)
        }
        if (SharePref.getBooleanPref(this,"notificationbar")){
            imgTurnNoti.setImageResource(R.drawable.ic_switch_on)
        }else{
            if (imgTurnNoti != null) imgTurnNoti.setImageResource(R.drawable.ic_switch_off)
        }
        if (SharePref.getBooleanPref(this,"voicecallbar")){
            imgTurnVoice.setImageResource(R.drawable.ic_switch_on)
        }else{
            if (imgTurnVoice != null) imgTurnVoice.setImageResource(R.drawable.ic_switch_off)
        }
        imgBack.setOnClickListener {
           onBackPressed()
        }
        imgTurn.setOnClickListener {
            if ((imgTurn != null) && (imgTurnShown)){
                imgTurn.setImageResource(R.drawable.ic_switch_on)
                imgTurnShown = false
                SharePref.setBooleanPref(this,"brightbar",true)
            }
            else{
                if (imgTurn != null) imgTurn.setImageResource(R.drawable.ic_switch_off)
                imgTurnShown = true
                SharePref.setBooleanPref(this,"brightbar",false)
            }
        }
        imgTurnMedia.setOnClickListener {
            if ((imgTurnMedia != null) && (imgTurnMediaShown)){
                imgTurnMedia.setImageResource(R.drawable.ic_switch_on);
                imgTurnMediaShown = false
                SharePref.setBooleanPref(this,"mediabar",true)
            }
            else{
                if (imgTurnMedia != null) imgTurnMedia.setImageResource(R.drawable.ic_switch_off);
                imgTurnMediaShown = true
                SharePref.setBooleanPref(this,"mediabar",false)
            }
        }
        imgTurnAlarm.setOnClickListener(View.OnClickListener {
            if ((imgTurnAlarm != null) && (imgTurnAlarmShown)){
                imgTurnAlarm.setImageResource(R.drawable.ic_switch_on);
                imgTurnAlarmShown = false
                SharePref.setBooleanPref(this,"alarmbar",true)
            }
            else{
                if (imgTurnAlarm != null) imgTurnAlarm.setImageResource(R.drawable.ic_switch_off);
                imgTurnAlarmShown = true
                SharePref.setBooleanPref(this,"alarmbar",false)
            }
        })
//        imgTurnAlarm.setOnClickListener {
//
//        }
        imgTurnRing.setOnClickListener {
            if ((imgTurnRing != null) && (imgTurnRingShown)){
                imgTurnRing.setImageResource(R.drawable.ic_switch_on);
                imgTurnRingShown = false
                SharePref.setBooleanPref(this,"ringbar",true)
            }
            else{
                if (imgTurnRing != null) imgTurnRing.setImageResource(R.drawable.ic_switch_off);
                imgTurnRingShown = true
                SharePref.setBooleanPref(this,"ringbar",false)
            }
        }
        imgTurnNoti.setOnClickListener {
            if ((imgTurnNoti != null) && (imgTurnNotiShown)){
                imgTurnNoti.setImageResource(R.drawable.ic_switch_on);
                imgTurnNotiShown = false
                SharePref.setBooleanPref(this,"notificationbar",true)
            }
            else{
                if (imgTurnNoti != null) imgTurnNoti.setImageResource(R.drawable.ic_switch_off);
                imgTurnNotiShown = true
                SharePref.setBooleanPref(this,"notificationbar",false)
            }
        }
        imgTurnVoice.setOnClickListener {
            if ((imgTurnVoice != null) && (imgTurnVoiceShown)){
                imgTurnVoice.setImageResource(R.drawable.ic_switch_on);
                imgTurnVoiceShown = false
                SharePref.setBooleanPref(this,"voicecallbar",true)
            }
            else{
                if (imgTurnVoice != null) imgTurnVoice.setImageResource(R.drawable.ic_switch_off);
                imgTurnVoiceShown = true
                SharePref.setBooleanPref(this,"voicecallbar",false)
            }
        }

    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java);
        startActivity(intent)
        finish()
    }
}