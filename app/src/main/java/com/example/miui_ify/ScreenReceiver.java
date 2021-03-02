package com.example.miui_ify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenReceiver extends BroadcastReceiver {


    private boolean screenOff;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            screenOff = true;
            Log.i("screenLog", "screen off");

        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            screenOff = false;
            Log.i("screenLog", "screen on");
            Intent intent1 = new Intent(context, FloatingWindow.class);
            context.stopService(intent1);
            context.startService(intent1);
        }
    }
}