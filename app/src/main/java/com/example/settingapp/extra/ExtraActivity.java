package com.example.settingapp.extra;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.miui_ify.MainActivity;
import com.example.settingapp.BottomStatusBar.BottomStatusActivity;
import com.example.settingapp.R;

public class ExtraActivity extends AppCompatActivity {

    private boolean img_lock_screenShown= true;
    private boolean img_vibrateShown= true;
    private boolean img_feedbackShown= true;
    private boolean img_swipeShown= true;
    private boolean img_smoothShown= true;
    private boolean img_open_panelShown= true;
    private boolean img_auto_closeShown= true;
    private boolean img_ssidShown= true;


    ImageView imgBack;
    ImageView img_lock_screen,img_vibrate,img_feedback,img_swipe,img_smooth,img_open_panel,img_auto_close,img_ssid;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);
        Window window = getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(Color.parseColor("#ffffff"));// set status background white

        imgBack = findViewById(R.id.imgBack);

        img_lock_screen = findViewById(R.id.img_lock_screen);
        img_vibrate = findViewById(R.id.img_vibrate);
        img_feedback = findViewById(R.id.img_feedback);
        img_swipe = findViewById(R.id.img_swipe);
        img_smooth = findViewById(R.id.img_smooth);
        img_open_panel = findViewById(R.id.img_open_panel);
        img_auto_close = findViewById(R.id.img_auto_close);
        img_ssid = findViewById(R.id.img_ssid);

        img_lock_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((img_lock_screen != null) && (img_lock_screenShown)){
                    img_lock_screen.setImageResource(R.drawable.ic_switch_on);
                    img_lock_screenShown = false;
                }
                else {
                    if (img_lock_screen != null) img_lock_screen.setImageResource(R.drawable.ic_switch_off);
                    img_lock_screenShown = true;
                }
            }
        });

        img_vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((img_vibrate != null) && (img_vibrateShown)){
                    img_vibrate.setImageResource(R.drawable.ic_switch_on);
                    img_vibrateShown = false;
                }
                else {
                    if (img_vibrate != null) img_vibrate.setImageResource(R.drawable.ic_switch_off);
                    img_vibrateShown = true;
                }
            }
        });

        img_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((img_feedback != null) && (img_feedbackShown)){
                    img_feedback.setImageResource(R.drawable.ic_switch_on);
                    img_feedbackShown = false;
                }
                else {
                    if (img_feedback != null) img_feedback.setImageResource(R.drawable.ic_switch_off);
                    img_feedbackShown = true;
                }
            }
        });

        img_swipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((img_swipe != null) && (img_swipeShown)){
                    img_swipe.setImageResource(R.drawable.ic_switch_on);
                    img_swipeShown = false;
                }
                else {
                    if (img_swipe != null) img_swipe.setImageResource(R.drawable.ic_switch_off);
                    img_swipeShown = true;
                }
            }
        });

        img_smooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((img_smooth != null) && (img_smoothShown)){
                    img_smooth.setImageResource(R.drawable.ic_switch_on);
                    img_smoothShown = false;
                }
                else {
                    if (img_smooth != null) img_smooth.setImageResource(R.drawable.ic_switch_off);
                    img_smoothShown = true;
                }
            }
        });

        img_open_panel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((img_open_panel != null) && (img_open_panelShown)){
                    img_open_panel.setImageResource(R.drawable.ic_switch_on);
                    img_open_panelShown = false;
                }
                else {
                    if (img_open_panel != null) img_open_panel.setImageResource(R.drawable.ic_switch_off);
                    img_open_panelShown = true;
                }
            }
        });

        img_auto_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((img_auto_close != null) && (img_auto_closeShown)){
                    img_auto_close.setImageResource(R.drawable.ic_switch_on);
                    img_auto_closeShown = false;
                }
                else {
                    if (img_auto_close != null) img_open_panel.setImageResource(R.drawable.ic_switch_off);
                    img_auto_closeShown = true;
                }
            }
        });

        img_ssid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((img_ssid != null) && (img_ssidShown)){
                    img_ssid.setImageResource(R.drawable.ic_switch_on);
                    img_ssidShown = false;
                }
                else {
                    if (img_ssid != null) img_ssid.setImageResource(R.drawable.ic_switch_off);
                    img_ssidShown = true;
                }
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ExtraActivity.this, MainActivity.class));
        finish();
    }
}