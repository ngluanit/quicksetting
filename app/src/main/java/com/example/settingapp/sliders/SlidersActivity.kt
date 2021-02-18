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

        imgBack.setOnClickListener {
           onBackPressed()
        }
        imgTurn.setOnClickListener {
            if ((imgTurn != null) && (imgTurnShown)){
                imgTurn.setImageResource(R.drawable.ic_switch_on);
                imgTurnShown = false
            }
            else{
                if (imgTurn != null) imgTurn.setImageResource(R.drawable.ic_switch_off);
                imgTurnShown = true
            }
        }
        imgTurnMedia.setOnClickListener {
            if ((imgTurnMedia != null) && (imgTurnMediaShown)){
                imgTurnMedia.setImageResource(R.drawable.ic_switch_on);
                imgTurnMediaShown = false
            }
            else{
                if (imgTurnMedia != null) imgTurnMedia.setImageResource(R.drawable.ic_switch_off);
                imgTurnMediaShown = true
            }
        }
        imgTurnAlarm.setOnClickListener {
            if ((imgTurnAlarm != null) && (imgTurnAlarmShown)){
                imgTurnAlarm.setImageResource(R.drawable.ic_switch_on);
                imgTurnAlarmShown = false
            }
            else{
                if (imgTurnAlarm != null) imgTurnAlarm.setImageResource(R.drawable.ic_switch_off);
                imgTurnAlarmShown = true
            }
        }
        imgTurnRing.setOnClickListener {
            if ((imgTurnRing != null) && (imgTurnRingShown)){
                imgTurnRing.setImageResource(R.drawable.ic_switch_on);
                imgTurnRingShown = false
            }
            else{
                if (imgTurnRing != null) imgTurnRing.setImageResource(R.drawable.ic_switch_off);
                imgTurnRingShown = true
            }
        }
        imgTurnNoti.setOnClickListener {
            if ((imgTurnNoti != null) && (imgTurnNotiShown)){
                imgTurnNoti.setImageResource(R.drawable.ic_switch_on);
                imgTurnNotiShown = false
            }
            else{
                if (imgTurnNoti != null) imgTurnNoti.setImageResource(R.drawable.ic_switch_off);
                imgTurnNotiShown = true
            }
        }
        imgTurnVoice.setOnClickListener {
            if ((imgTurnVoice != null) && (imgTurnVoiceShown)){
                imgTurnVoice.setImageResource(R.drawable.ic_switch_on);
                imgTurnVoiceShown = false
            }
            else{
                if (imgTurnVoice != null) imgTurnVoice.setImageResource(R.drawable.ic_switch_off);
                imgTurnVoiceShown = true
            }
        }

    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java);
        startActivity(intent)
        finish()
    }
}