package com.example.systemIcons;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.settingapp.R;

public class SystemIcons extends AppCompatActivity {

    RelativeLayout rlPlane ,rlAlarm,rlRotate,rlBattery,rlTextBattery,rlBluetooth,rlDisturb,rlHeadset,rlHotspot,rlLocation,rlMobiedata,rlNFC,rlSound,rlSync,rlVPN,rlWifi;
    ImageView imgTurnPlane ,imgTurnAlarm,imgTurnRotate,imgTurnBattery,imgTurnTextBattery,imgTurnBluetooth,imgTurnDisturb,imgTurnHeadset,imgTurnHotspot,imgTurnLocation,imgTurnMobiedata,imgTurnNFC,imgTurnSound,imgTurnSync,imgTurnVPN,imgTurnWifi;

    private boolean imgTurnPlaneShown= true;
    private boolean imgTurnAlarmShown= true;
    private boolean imgTurnRotateShown= true;
    private boolean imgTurnBatteryShown= true;
    private boolean imgTurnTextBatteryShown= true;
    private boolean imgTurnBluetoothShown= true;
    private boolean imgTurnDisturbShown= true;
    private boolean imgTurnHeadsetShown= true;
    private boolean imgTurnHotspotShown= true;
    private boolean imgTurnLocationShown= true;
    private boolean imgTurnMobiedataShown= true;
    private boolean imgTurnNFCShown= true;
    private boolean imgTurnSoundShown= true;
    private boolean imgTurnSyncShown= true;
    private boolean imgTurnVPNShown= true;
    private boolean imgTurnWifiShown= true;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_icons);
        Window window = getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(Color.parseColor("#f0f0f0"));// set status background white

        rlPlane = findViewById(R.id.rlPlane);
        rlAlarm = findViewById(R.id.rlAlarm);
        rlRotate = findViewById(R.id.rlRotate);
        rlBattery = findViewById(R.id.rlBattery);
        rlTextBattery = findViewById(R.id.rlTextBattery);
        rlBluetooth = findViewById(R.id.rlBluetooth);
        rlDisturb = findViewById(R.id.rlDisturb);
        rlHeadset = findViewById(R.id.rlHeadset);
        rlHotspot = findViewById(R.id.rlHotspot);
        rlLocation = findViewById(R.id.rlLocation);
        rlMobiedata = findViewById(R.id.rlMobiedata);
        rlNFC = findViewById(R.id.rlNFC);
        rlSound = findViewById(R.id.rlSound);
        rlSync = findViewById(R.id.rlSync);
        rlVPN = findViewById(R.id.rlVPN);
        rlWifi = findViewById(R.id.rlWifi);

        imgTurnPlane = findViewById(R.id.imgTurnPlane);
        imgTurnAlarm = findViewById(R.id.imgTurnAlarm);
        imgTurnRotate = findViewById(R.id.imgTurnRotate);
        imgTurnBattery = findViewById(R.id.imgTurnBattery);
        imgTurnTextBattery = findViewById(R.id.imgTurnTextBattery);
        imgTurnBluetooth = findViewById(R.id.imgTurnBluetooth);
        imgTurnDisturb = findViewById(R.id.imgTurnDisturb);
        imgTurnHeadset = findViewById(R.id.imgTurnHeadset);
        imgTurnHotspot = findViewById(R.id.imgTurnHotspot);
        imgTurnLocation = findViewById(R.id.imgTurnLocation);
        imgTurnMobiedata = findViewById(R.id.imgTurnMobiedata);
        imgTurnNFC = findViewById(R.id.imgTurnNFC);
        imgTurnSound = findViewById(R.id.imgTurnSound);
        imgTurnSync = findViewById(R.id.imgTurnSync);
        imgTurnVPN = findViewById(R.id.imgTurnVPN);
        imgTurnWifi = findViewById(R.id.imgTurnWifi);

        rlPlane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgTurnPlane != null) && (imgTurnPlaneShown)){
                    imgTurnPlane.setImageResource(R.drawable.ic_switch_on);
                    imgTurnPlaneShown = false;
                }
                else {
                    if (imgTurnPlane != null) imgTurnPlane.setImageResource(R.drawable.ic_switch_off);
                    imgTurnPlaneShown = true;
                }
            }
        });
        rlAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgTurnAlarm != null) && (imgTurnAlarmShown)){
                    imgTurnAlarm.setImageResource(R.drawable.ic_switch_on);
                    imgTurnAlarmShown = false;
                }
                else {
                    if (imgTurnAlarm != null) imgTurnAlarm.setImageResource(R.drawable.ic_switch_off);
                    imgTurnAlarmShown = true;
                }
            }
        });
        rlRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgTurnRotate != null) && (imgTurnRotateShown)){
                    imgTurnRotate.setImageResource(R.drawable.ic_switch_on);
                    imgTurnRotateShown = false;
                }
                else {
                    if (imgTurnRotate != null) imgTurnRotate.setImageResource(R.drawable.ic_switch_off);
                    imgTurnRotateShown = true;
                }
            }
        });
        rlBattery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgTurnBattery != null) && (imgTurnBatteryShown)){
                    imgTurnBattery.setImageResource(R.drawable.ic_switch_on);
                    imgTurnBatteryShown = false;
                }
                else {
                    if (imgTurnBattery != null) imgTurnBattery.setImageResource(R.drawable.ic_switch_off);
                    imgTurnBatteryShown = true;
                }
            }
        });
        rlTextBattery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgTurnTextBattery != null) && (imgTurnTextBatteryShown)){
                    imgTurnTextBattery.setImageResource(R.drawable.ic_switch_on);
                    imgTurnTextBatteryShown = false;
                }
                else {
                    if (imgTurnTextBattery != null) imgTurnTextBattery.setImageResource(R.drawable.ic_switch_off);
                    imgTurnTextBatteryShown = true;
                }
            }
        });
        rlBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgTurnBluetooth != null) && (imgTurnBluetoothShown)){
                    imgTurnBluetooth.setImageResource(R.drawable.ic_switch_on);
                    imgTurnBluetoothShown = false;
                }
                else {
                    if (imgTurnBluetooth != null) imgTurnBluetooth.setImageResource(R.drawable.ic_switch_off);
                    imgTurnTextBatteryShown = true;
                }
            }
        });
        rlDisturb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgTurnDisturb != null) && (imgTurnDisturbShown)){
                    imgTurnDisturb.setImageResource(R.drawable.ic_switch_on);
                    imgTurnDisturbShown = false;
                }
                else {
                    if (imgTurnDisturb != null) imgTurnDisturb.setImageResource(R.drawable.ic_switch_off);
                    imgTurnDisturbShown = true;
                }
            }
        });
        rlHeadset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgTurnHeadset != null) && (imgTurnHeadsetShown)){
                    imgTurnHeadset.setImageResource(R.drawable.ic_switch_on);
                    imgTurnHeadsetShown = false;
                }
                else {
                    if (imgTurnHeadset != null) imgTurnHeadset.setImageResource(R.drawable.ic_switch_off);
                    imgTurnHeadsetShown = true;
                }
            }
        });
        rlHotspot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgTurnHotspot != null) && (imgTurnHotspotShown)){
                    imgTurnHotspot.setImageResource(R.drawable.ic_switch_on);
                    imgTurnHotspotShown = false;
                }
                else {
                    if (imgTurnHotspot != null) imgTurnHotspot.setImageResource(R.drawable.ic_switch_off);
                    imgTurnHotspotShown = true;
                }
            }
        });
        rlLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgTurnLocation != null) && (imgTurnLocationShown)){
                    imgTurnLocation.setImageResource(R.drawable.ic_switch_on);
                    imgTurnLocationShown = false;
                }
                else {
                    if (imgTurnLocation != null) imgTurnLocation.setImageResource(R.drawable.ic_switch_off);
                    imgTurnLocationShown = true;
                }
            }
        });
        rlMobiedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgTurnMobiedata != null) && (imgTurnMobiedataShown)){
                    imgTurnMobiedata.setImageResource(R.drawable.ic_switch_on);
                    imgTurnMobiedataShown = false;
                }
                else {
                    if (imgTurnMobiedata != null) imgTurnMobiedata.setImageResource(R.drawable.ic_switch_off);
                    imgTurnMobiedataShown = true;
                }
            }
        });
        rlNFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgTurnNFC != null) && (imgTurnNFCShown)){
                    imgTurnNFC.setImageResource(R.drawable.ic_switch_on);
                    imgTurnNFCShown = false;
                }
                else {
                    if (imgTurnNFC != null) imgTurnNFC.setImageResource(R.drawable.ic_switch_off);
                    imgTurnNFCShown = true;
                }
            }
        });
        rlSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgTurnSound != null) && (imgTurnSoundShown)){
                    imgTurnSound.setImageResource(R.drawable.ic_switch_on);
                    imgTurnSoundShown = false;
                }
                else {
                    if (imgTurnSound != null) imgTurnSound.setImageResource(R.drawable.ic_switch_off);
                    imgTurnSoundShown = true;
                }
            }
        });
        rlSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgTurnSync != null) && (imgTurnSyncShown)){
                    imgTurnSync.setImageResource(R.drawable.ic_switch_on);
                    imgTurnSyncShown = false;
                }
                else {
                    if (imgTurnSync != null) imgTurnSync.setImageResource(R.drawable.ic_switch_off);
                    imgTurnSyncShown = true;
                }
            }
        });
        rlVPN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgTurnVPN != null) && (imgTurnVPNShown)){
                    imgTurnVPN.setImageResource(R.drawable.ic_switch_on);
                    imgTurnVPNShown = false;
                }
                else {
                    if (imgTurnVPN != null) imgTurnVPN.setImageResource(R.drawable.ic_switch_off);
                    imgTurnVPNShown = true;
                }
            }
        });
        rlWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgTurnWifi != null) && (imgTurnWifiShown)){
                    imgTurnWifi.setImageResource(R.drawable.ic_switch_on);
                    imgTurnWifiShown = false;
                }
                else {
                    if (imgTurnWifi != null) imgTurnWifi.setImageResource(R.drawable.ic_switch_off);
                    imgTurnWifiShown = true;
                }
            }
        });

    }
}