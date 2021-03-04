package com.example.miui_ify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent1);
            }else {
                context.startService(intent1);
            }
        }
    }
}