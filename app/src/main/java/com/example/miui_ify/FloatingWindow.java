package com.example.miui_ify;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.settingapp.R;
import com.example.settingapp.tiles.ItemNotification;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import static android.hardware.Camera.Parameters.FLASH_MODE_AUTO;
import static android.hardware.Camera.Parameters.FLASH_MODE_ON;
import static android.hardware.camera2.CameraMetadata.FLASH_MODE_TORCH;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class FloatingWindow extends Service implements IconSettingAdapter.ItemClick {

    private Context mContext;
    private WindowManager mWindowManager;
    private View mView;
    private SeekBar seekBar;
    //Variable to store brightness value
    private int brightness;
    //Content resolver used as a handle to the system's settings
    private ContentResolver cResolver;
    //Window object, that will store a reference to the current window
    private Window window;
    private SlidingUpPanelLayout mLayout;
    ArrayList<String> text1 = new ArrayList<>();
    ArrayList<Integer> posts = new ArrayList();
    ArrayList<ItemNotification> list = new ArrayList<>();
    RecyclerView rcvIconSetting;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        text1.add("Wifi");
        text1.add("Mobile data");
        text1.add("Bluetooth");
        text1.add("Sync");
        text1.add("Location");
        text1.add("Auto-rotate");
        text1.add("Do not disturb");
        text1.add("Torch");
        posts.add(R.drawable.ic_wifi_n);
        posts.add(R.drawable.ic_data_n);
        posts.add(R.drawable.ic_bluetooth_n);
        posts.add(R.drawable.ic_sync_n);
        posts.add(R.drawable.ic_location_n);
        posts.add(R.drawable.ic_auto_rotate_n);
        posts.add(R.drawable.ic_disturb_n);
        posts.add(R.drawable.ic_torch_n);
        for (int i = 0; i < 8; i++) {
            ItemNotification itemNotification = new ItemNotification();
            itemNotification.setImg(posts.get(i));
            itemNotification.setName(text1.get(i));
            list.add(itemNotification);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        allAboutLayout(intent);
        moveView();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        if (mView != null) {
            mWindowManager.removeView(mView);
        }
        super.onDestroy();
    }

    WindowManager.LayoutParams mWindowsParams;

    private void moveView() {
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        int width = (int) (metrics.widthPixels * 1f);
        int height = (int) (metrics.heightPixels * 1f);
        mWindowsParams = new WindowManager.LayoutParams(
                width,
                height,
                //WindowManager.LayoutParams.WRAP_CONTENT,
                //WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                (Build.VERSION.SDK_INT <= 25) ? WindowManager.LayoutParams.TYPE_PHONE : WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
                ,
                //WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, // Not displaying keyboard on bg activity's EditText
                // WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, //Not work with EditText on keyboard
                PixelFormat.TRANSLUCENT);
        mWindowsParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        //params.x = 0;
        //        mWindowsParams.y = 100;

        mWindowManager.addView(mView, mWindowsParams);
    }

    private boolean isViewInBounds(View view, int x, int y) {
        Rect outRect = new Rect();
        int[] location = new int[2];
        view.getDrawingRect(outRect);
        view.getLocationOnScreen(location);
        outRect.offset(location[0], location[1]);
        return outRect.contains(x, y);
    }


    private void allAboutLayout(Intent intent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = layoutInflater.inflate(R.layout.ovelay_window, null);
        final PopupWindow popupWindow = new PopupWindow(mView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(false);
        Button btnClose = (Button) mView.findViewById(R.id.btnClose);
        seekBar = (SeekBar) mView.findViewById(R.id.BrightBar);
        cResolver = getContentResolver();
        BrightnessControl(seekBar);
        mLayout = (SlidingUpPanelLayout) mView.findViewById(R.id.sliding_layout);

        TextView tvDate, tvHour;
        rcvIconSetting = mView.findViewById(R.id.rcvIconSetting);
        rcvIconSetting.setLayoutManager(new GridLayoutManager(this, 4));
        int powifi = -1;
        int podata = -1;
        int pobluetooth = -1;
        int polocation = -1;
        int porotetion=-1;
        for (int i = 0; i < text1.size(); i++) {
            if (text1.get(i).contains("Wifi")) {
                powifi = i;
            } else if (text1.get(i).contains("Mobile data")) {
                podata = i;
            } else if (text1.get(i).contains("Bluetooth")) {
                pobluetooth = i;
            } else if (text1.get(i).contains("Location")) {
                polocation = i;
            }else if (text1.get(i).contains("Auto-rotate")){
                porotetion=i;
            }
        }
        WifiManager wifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        boolean wifiEnabled = wifiManager.isWifiEnabled();
        boolean mobileDataAllowed = Settings.Secure.getInt(getContentResolver(), "mobile_data", 1) == 1;
        if (mobileDataAllowed && podata != -1) {
            list.get(podata).setImg(R.drawable.ic_data_e);
        }
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter.isEnabled()) {
            list.get(pobluetooth).setImg(R.drawable.ic_bluetooth_e);
        }
        if (isLocationEnabled(this)) {
            list.get(polocation).setImg(R.drawable.ic_location_e);
        } else {
            list.get(polocation).setImg(R.drawable.ic_location_n);
        }
        if (getRotationScreenFromSettingsIsEnabled(this)){
            list.get(porotetion).setImg(R.drawable.ic_auto_rotate_e);
        }else {
            list.get(porotetion).setImg(R.drawable.ic_auto_rotate_n);
        }
        WifiManager manager1 = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (manager1.isWifiEnabled()) {
            WifiInfo wifiInfo = manager1.getConnectionInfo();
            if (wifiInfo != null) {
                NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
                if (state == NetworkInfo.DetailedState.CONNECTED || state == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
                    System.out.println("11112222///" + wifiInfo.getSSID());
                    if (wifiEnabled) {
                        if (powifi != -1) {
                            list.get(powifi).setName(wifiInfo.getSSID());
                            list.get(powifi).setImg(R.drawable.ic_wifi_e);
                        }
                    } else {

                    }
                }
            }
        }
        IconSettingAdapter iconSettingAdapter = new IconSettingAdapter(list);
        iconSettingAdapter.setIClick(this);
        rcvIconSetting.setAdapter(iconSettingAdapter);
        ImageView imgEdit, imgSetting, imgProfile;
        tvDate = (TextView) mView.findViewById(R.id.tvDate);
        tvHour = (TextView) mView.findViewById(R.id.tvHour);
        imgEdit = (ImageView) mView.findViewById(R.id.imgEdit);
        imgSetting = (ImageView) mView.findViewById(R.id.imgSetting);
        imgProfile = (ImageView) mView.findViewById(R.id.imgProfile);
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSelf();
                startActivity(new Intent(Settings.ACTION_SETTINGS));
            }
        });

        DateFormat df = new SimpleDateFormat("EEE,MMM d");
        String date = df.format(Calendar.getInstance().getTime());
        tvDate.setText(date);
        DateFormat dfhour = new SimpleDateFormat("HH:mm");
        String hour = dfhour.format(Calendar.getInstance().getTime());
        tvHour.setText(hour);
        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i("TAG", "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i("TAG", "onPanelSlide, offset ");
            }


        });
        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
            }
        });
        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSelf();

                // seekBar.setVisibility(View.VISIBLE);
            }
        });

    }

    private void BrightnessControl(SeekBar seekBar) {
        //Set the seekbar range between 0 and 255
        //seek bar settings//
        //sets the range between 0 and 255
        seekBar.setMax(255);
        //set the seek bar progress to 1
        seekBar.setKeyProgressIncrement(1);

        try {
            //Get the current system brightness
            brightness = Settings.System.getInt(cResolver, Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            //Throw an error case it couldn't be retrieved
            Log.e("Error", "Cannot access system brightness");
            e.printStackTrace();
        }

        //Set the progress of the seek bar based on the system's brightness
        seekBar.setProgress(brightness);

        //Register OnSeekBarChangeListener, so it can actually change values
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Set the system brightness using the brightness variable value
                Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                //Nothing handled here
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Set the minimal brightness level
                //if seek bar is 20 or any value below
                if (progress <= 20) {
                    //Set the brightness to 20
                    brightness = 20;
                } else //brightness is greater than 20
                {
                    //Set brightness variable based on the progress bar
                    brightness = progress;
                }
                //Calculate the brightness percentage
                float perc = (brightness / (float) 255) * 100;
                //Set the brightness percentage
                //  txtPerc.setText((int)perc +" %");
            }
        });
    }

    private void hideKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @Override
    public void onItemclick1(int position) {
        int powifi = -1;
        int podata = -1;
        int pobluetooth = -1;
        if (text1.get(position).contains("Wifi")) {
            powifi = position;
            if (list.get(position).getImg().equals(R.drawable.ic_wifi_n)) {
                WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifi.setWifiEnabled(true);
                if (wifi.isWifiEnabled()) {
                    WifiInfo wifiInfo = wifi.getConnectionInfo();
                    if (wifiInfo != null) {
                        NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
                        if (state == NetworkInfo.DetailedState.CONNECTED || state == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
                            if (wifi.isWifiEnabled()) {
                                if (powifi != -1) {
                                    list.get(powifi).setName(wifiInfo.getSSID());
                                }
                            }
                        }
                    }
                }
                list.get(position).setImg(R.drawable.ic_wifi_e);
            } else {
                list.get(position).setImg(R.drawable.ic_wifi_n);
                list.get(position).setName("Wifi");
                WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifi.setWifiEnabled(false);
            }
        } else if (text1.get(position).contains("Mobile data")) {
            podata = position;
            if (list.get(position).getImg().equals(R.drawable.ic_data_n)) {
                list.get(position).setImg(R.drawable.ic_data_e);
            } else {
                list.get(position).setImg(R.drawable.ic_data_n);
            }
        } else if (text1.get(position).contains("Bluetooth")) {
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (list.get(position).getImg().equals(R.drawable.ic_bluetooth_n)) {
                list.get(position).setImg(R.drawable.ic_bluetooth_e);
                mBluetoothAdapter.enable();
            } else {
                list.get(position).setImg(R.drawable.ic_bluetooth_n);
                mBluetoothAdapter.disable();
            }
        }else if (text1.get(position).contains("Auto-rotate")){
            if (list.get(position).getImg().equals(R.drawable.ic_auto_rotate_n)){
                list.get(position).setImg(R.drawable.ic_auto_rotate_e);
                setRotationScreenFromSettings(this,true);
            }else {
                list.get(position).setImg(R.drawable.ic_auto_rotate_n);
                setRotationScreenFromSettings(this,false);
            }
        }
        IconSettingAdapter iconSettingAdapter = new IconSettingAdapter(list);
        iconSettingAdapter.setIClick(this::onItemclick1);
        iconSettingAdapter.notifyDataSetChanged();
        rcvIconSetting.setAdapter(iconSettingAdapter);
    }

    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    public static void setRotationScreenFromSettings(Context context, boolean enabled)
    {
        try
        {
            if (Settings.System.getInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION) == 1)
            {
                Display defaultDisplay = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                Settings.System.putInt(context.getContentResolver(), Settings.System.USER_ROTATION, defaultDisplay.getRotation());
                Settings.System.putInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0);
            }
            else
            {
                Settings.System.putInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 1);
            }

            Settings.System.putInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, enabled ? 1 : 0);

        }
        catch (Settings.SettingNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public static boolean getRotationScreenFromSettingsIsEnabled(Context context) {
        int result = 0;
        try {
            result = Settings.System.getInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return result == 1;
    }
    public void turnFlash(boolean isTurn){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            String cameraId = null;
            try {
                cameraId = camManager.getCameraIdList()[0];
                camManager.setTorchMode(cameraId, isTurn);

            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }
}

