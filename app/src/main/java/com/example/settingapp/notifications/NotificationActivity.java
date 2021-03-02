package com.example.settingapp.notifications;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.blacklist.BlacklistActivity;
import com.example.miui_ify.MainActivity;
import com.example.miui_ify.MainActivity;
import com.example.settingapp.BottomStatusBar.BottomStatusActivity;
import com.example.settingapp.R;

public class NotificationActivity extends AppCompatActivity {

    ImageView switch_lockscreen,switch_MIUI,swich_background,switch_dynamic,switch_blacklist,switch_hide_presi,switch_music,
            switch_remove, switch_expand,switch_clear,switch_auto_close;
    ImageView imgBack;
    RelativeLayout rlblack_list;

    private boolean switch_lockscreenShown= true;
    private boolean switch_MIUIShown= true;
    private boolean swich_backgroundShown= true;
    private boolean switch_dynamicShown= true;
    private boolean switch_blacklistShown= true;
    private boolean switch_hide_presiShown= true;
    private boolean switch_musicShown= true;
    private boolean switch_removeShown= true;
    private boolean switch_expandShown= true;
    private boolean switch_clearShown= true;
    private boolean switch_auto_closeShown= true;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Window window = getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(Color.parseColor("#f0f0f0"));// set status background white

        imgBack = findViewById(R.id.imgBack);
        switch_lockscreen = findViewById(R.id.switch_lockscreen);
        switch_MIUI = findViewById(R.id.switch_MIUI);
        swich_background = findViewById(R.id.swich_background);
        switch_dynamic = findViewById(R.id.switch_dynamic);

        switch_hide_presi = findViewById(R.id.switch_hide_presi);
        switch_music = findViewById(R.id.switch_music);
        switch_remove = findViewById(R.id.switch_remove);
        switch_expand = findViewById(R.id.switch_expand);
        switch_clear = findViewById(R.id.switch_clear);
        switch_auto_close = findViewById(R.id.switch_auto_close);

        rlblack_list = findViewById(R.id.rlblack_list);

        rlblack_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NotificationActivity.this, BlacklistActivity.class));
            }
        });


        switch_lockscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((switch_lockscreen != null) && (switch_lockscreenShown)){
                    switch_lockscreen.setImageResource(R.drawable.ic_switch_on);
                    switch_lockscreenShown = false;
                }
                else {
                    if (switch_lockscreen != null) switch_lockscreen.setImageResource(R.drawable.ic_switch_off);
                    switch_lockscreenShown = true;
                }
            }
        });

        switch_MIUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((switch_MIUI != null) && (switch_MIUIShown)){
                    switch_MIUI.setImageResource(R.drawable.ic_switch_on);
                    switch_MIUIShown = false;
                }
                else {
                    if (switch_MIUI != null) switch_MIUI.setImageResource(R.drawable.ic_switch_off);
                    switch_MIUIShown = true;
                }
            }
        });

        swich_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((swich_background != null) && (swich_backgroundShown)){
                    swich_background.setImageResource(R.drawable.ic_switch_on);
                    swich_backgroundShown = false;
                }
                else {
                    if (swich_background != null) swich_background.setImageResource(R.drawable.ic_switch_off);
                    swich_backgroundShown = true;
                }
            }
        });

        switch_dynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((switch_dynamic != null) && (switch_dynamicShown)){
                    switch_dynamic.setImageResource(R.drawable.ic_switch_on);
                    switch_dynamicShown = false;
                }
                else {
                    if (switch_dynamic != null) switch_dynamic.setImageResource(R.drawable.ic_switch_off);
                    switch_dynamicShown = true;
                }
            }
        });


        switch_hide_presi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((switch_hide_presi != null) && (switch_hide_presiShown)){
                    switch_hide_presi.setImageResource(R.drawable.ic_switch_on);
                    switch_hide_presiShown = false;
                }
                else {
                    if (switch_hide_presi != null) switch_hide_presi.setImageResource(R.drawable.ic_switch_off);
                    switch_hide_presiShown = true;
                }
            }
        });

        switch_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((switch_music != null) && (switch_musicShown)){
                    switch_music.setImageResource(R.drawable.ic_switch_on);
                    switch_musicShown = false;
                }
                else {
                    if (switch_music != null) switch_music.setImageResource(R.drawable.ic_switch_off);
                    switch_musicShown = true;
                }
            }
        });

        switch_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((switch_remove != null) && (switch_removeShown)){
                    switch_remove.setImageResource(R.drawable.ic_switch_on);
                    switch_removeShown = false;
                }
                else {
                    if (switch_remove != null) switch_remove.setImageResource(R.drawable.ic_switch_off);
                    switch_removeShown = true;
                }
            }
        });

        switch_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((switch_expand != null) && (switch_expandShown)){
                    switch_expand.setImageResource(R.drawable.ic_switch_on);
                    switch_expandShown = false;
                }
                else {
                    if (switch_expand != null) switch_expand.setImageResource(R.drawable.ic_switch_off);
                    switch_expandShown = true;
                }
            }
        });

        switch_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((switch_clear != null) && (switch_clearShown)){
                    switch_clear.setImageResource(R.drawable.ic_switch_on);
                    switch_clearShown = false;
                }
                else {
                    if (switch_clear != null) switch_clear.setImageResource(R.drawable.ic_switch_off);
                    switch_clearShown = true;
                }
            }
        });

        switch_auto_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((switch_auto_close != null) && (switch_auto_closeShown)){
                    switch_auto_close.setImageResource(R.drawable.ic_switch_on);
                    switch_auto_closeShown = false;
                }
                else {
                    if (switch_auto_close != null) switch_auto_close.setImageResource(R.drawable.ic_switch_off);
                    switch_auto_closeShown = true;
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
        startActivity(new Intent(NotificationActivity.this, MainActivity.class));
        finish();
    }
}