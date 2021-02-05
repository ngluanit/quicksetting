package com.example.settingapp.BottomStatusBar;

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
import com.example.settingapp.R;

public class BottomStatusActivity extends AppCompatActivity {
    ImageView imgBack;
    ImageView swich_show_bottom,switch_disable,switch_show_notification,img_show_icon,switch_blacklist,switch_hide_while,switch_hide_landscape;

    private boolean swich_show_bottomShown= true;
    private boolean switch_disableShown= true;
    private boolean switch_show_notificationShown= true;
    private boolean img_show_iconShown= true;
    private boolean switch_blacklistShown= true;
    private boolean switch_hide_whileShown= true;
    private boolean switch_hide_landscapeShown= true;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_status);
        Window window = getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(Color.parseColor("#f0f0f0"));// set status background white

        swich_show_bottom = findViewById(R.id.swich_show_bottom);
        switch_disable = findViewById(R.id.switch_disable);
        switch_show_notification = findViewById(R.id.switch_show_notification);
        img_show_icon = findViewById(R.id.img_show_icon);
        switch_blacklist = findViewById(R.id.switch_blacklist);
        switch_hide_while = findViewById(R.id.switch_hide_while);
        switch_hide_landscape = findViewById(R.id.switch_hide_landscape);
        imgBack = findViewById(R.id.imgBack);

        swich_show_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if ((swich_show_bottom != null) && (swich_show_bottomShown)){
                    swich_show_bottom.setImageResource(R.drawable.ic_switch_on);
                    swich_show_bottomShown = false;
              }
              else {
                  if (swich_show_bottom != null) swich_show_bottom.setImageResource(R.drawable.ic_switch_off);
                  swich_show_bottomShown = true;
              }
            }
        });

        switch_disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((switch_disable != null) && (switch_disableShown)){
                    switch_disable.setImageResource(R.drawable.ic_switch_on);
                    switch_disableShown = false;
                }
                else {
                    if (switch_disable != null) switch_disable.setImageResource(R.drawable.ic_switch_off);
                    switch_disableShown = true;
                }
            }
        });

        switch_show_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((switch_show_notification != null) && (switch_show_notificationShown)){
                    switch_show_notification.setImageResource(R.drawable.ic_switch_on);
                    switch_show_notificationShown = false;
                }
                else {
                    if (switch_show_notification != null) switch_show_notification.setImageResource(R.drawable.ic_switch_off);
                    switch_show_notificationShown = true;
                }
            }
        });

        img_show_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((img_show_icon != null) && (img_show_iconShown)){
                    img_show_icon.setImageResource(R.drawable.ic_switch_on);
                    img_show_iconShown = false;
                }
                else {
                    if (img_show_icon != null) img_show_icon.setImageResource(R.drawable.ic_switch_off);
                    img_show_iconShown = true;
                }
            }
        });

        switch_blacklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((switch_blacklist != null) && (switch_blacklistShown)){
                    switch_blacklist.setImageResource(R.drawable.ic_switch_on);
                    switch_blacklistShown = false;
                }
                else {
                    if (switch_blacklist != null) switch_blacklist.setImageResource(R.drawable.ic_switch_off);
                    switch_blacklistShown = true;
                }
            }
        });

        switch_hide_while.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((switch_hide_while != null) && (switch_hide_whileShown)){
                    switch_hide_while.setImageResource(R.drawable.ic_switch_on);
                    switch_hide_whileShown = false;
                }
                else {
                    if (switch_hide_while != null) switch_hide_while.setImageResource(R.drawable.ic_switch_off);
                    switch_hide_whileShown = true;
                }
            }
        });

        switch_hide_landscape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((switch_hide_landscape != null) && (switch_hide_landscapeShown)){
                    switch_hide_landscape.setImageResource(R.drawable.ic_switch_on);
                    switch_hide_landscapeShown = false;
                }
                else {
                    if (switch_hide_landscape != null) switch_hide_landscape.setImageResource(R.drawable.ic_switch_off);
                    switch_hide_landscapeShown = true;
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
        startActivity(new Intent(BottomStatusActivity.this, MainActivity.class));
        finish();
    }
}