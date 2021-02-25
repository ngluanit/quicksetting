package com.example.miui_ify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadcastreceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startServiceIntent = new Intent(context, FloatingWindow.class);
        context.stopService(startServiceIntent);
        context.startService(startServiceIntent);
    }
}