package com.example.miui_ify;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

public class LockScreenTextService extends Service {

    private BroadcastReceiver mReceiver;
    private boolean isShowing = false;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    private WindowManager windowManager;
    private TextView textview;
    WindowManager.LayoutParams params;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager)getSystemService(WINDOW_SERVICE);

        //add textview and its properties
        textview = new TextView(this);
        textview.setText("Hello There!");
        textview.setTextColor(ContextCompat.getColor(this, android.R.color.black));
        textview.setTextSize(32f);

        //set parameters for the textview
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.BOTTOM;

        //Register receiver for determining screen off and if user is present
        mReceiver = new LockScreenStateReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);

        registerReceiver(mReceiver, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public class LockScreenStateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                //if screen is turn off show the textview
                if (!isShowing) {
                    windowManager.addView(textview, params);
                    isShowing = true;
                }
            }

            else if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
                //Handle resuming events if user is present/screen is unlocked remove the textview immediately
                if (isShowing) {
                    windowManager.removeViewImmediate(textview);
                    isShowing = false;
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        //unregister receiver when the service is destroy
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }

        //remove view if it is showing and the service is destroy
        if (isShowing) {
            windowManager.removeViewImmediate(textview);
            isShowing = false;
        }
        super.onDestroy();
    }

}
