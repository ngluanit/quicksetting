package com.example.settingapp.util;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.util.Log;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
@SuppressLint("OverrideAbstract")
public class NotifyListener extends NotificationListenerService {
    public static boolean listnerConnected = false;
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("name","onBind Called");
        listnerConnected = true;
        return super.onBind(intent);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.e("destroy", "called");
        listnerConnected = false;

    }
}