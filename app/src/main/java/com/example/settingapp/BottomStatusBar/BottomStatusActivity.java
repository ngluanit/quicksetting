package com.example.settingapp.BottomStatusBar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blacklist.BlacklistActivity;
import com.example.miui_ify.MainActivity;
import com.example.settingapp.R;
import com.example.settingapp.util.SharePref;

public class BottomStatusActivity extends AppCompatActivity {
    ImageView imgBack;
    ImageView swich_show_bottom,switch_disable,switch_hide_while,switch_hide_landscape;
    RelativeLayout rlblack_list;
    private boolean swich_show_bottomShown= true;
    private boolean switch_disableShown= true;
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
        switch_hide_while = findViewById(R.id.switch_hide_while);
        switch_hide_landscape = findViewById(R.id.switch_hide_landscape);
        rlblack_list = findViewById(R.id.rlblack_list);
        imgBack = findViewById(R.id.imgBack);


        rlblack_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BottomStatusActivity.this, BlacklistActivity.class));
            }
        });
        swich_show_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if ((swich_show_bottom != null) && (swich_show_bottomShown)){
                    swich_show_bottom.setImageResource(R.drawable.ic_switch_on);
                    swich_show_bottomShown = false;
                  SharePref.setBooleanPref(getApplicationContext(),"show_bottom",true);
              }
              else {
                  if (swich_show_bottom != null) swich_show_bottom.setImageResource(R.drawable.ic_switch_off);
                  swich_show_bottomShown = true;
                  SharePref.setBooleanPref(getApplicationContext(),"show_bottom",false);
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