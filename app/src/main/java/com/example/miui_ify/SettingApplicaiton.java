package com.example.miui_ify;

import android.app.Application;
import android.content.Intent;


public class SettingApplicaiton extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

//        KeyguardManager myKM = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
//        if( myKM.inKeyguardRestrictedInputMode()) {
//            Intent intent = new Intent(this, FloatingWindow.class);
//            intent.setAction("startagain");
//            startService(intent);
//        } else {
//            //it is not locked
//        }
    }
}
