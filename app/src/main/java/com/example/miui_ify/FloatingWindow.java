package com.example.miui_ify;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.settingapp.R;
import com.example.settingapp.tiles.ItemNotification;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.hardware.Camera.Parameters.FLASH_MODE_AUTO;
import static android.hardware.Camera.Parameters.FLASH_MODE_ON;
import static android.hardware.camera2.CameraMetadata.FLASH_MODE_TORCH;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class FloatingWindow extends Service implements IconSettingAdapter.ItemClick, LocationListener {
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 111;
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
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
    SlideUp slideUp;
    Camera camera;
    private Camera mCamera;
    private Camera.Parameters parameters;
    private CameraManager camManager;
    Activity activity;
    ImageView imgBluetooth,imgPin,imgMang,imgWifi,imgLoca,imgSound,imgRotate;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    LocationSettingsRequest mLocationSettingsRequest;
    LocationRequest mLocationRequest;

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
        //testLayout();
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
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                //WindowManager.LayoutParams.WRAP_CONTENT,
                //WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                (Build.VERSION.SDK_INT <= 25) ? WindowManager.LayoutParams.TYPE_PHONE : WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                //     | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                //     | WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW
                ,

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

    private void testLayout() {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = layoutInflater.inflate(R.layout.bottomsheetlayout, null);
        LinearLayout lnContainer = mView.findViewById(R.id.dragView);
        Button btnTest = mView.findViewById(R.id.btnTest);
        slideUp = new SlideUpBuilder(lnContainer)
                .withListeners(new SlideUp.Listener.Events() {
                    @Override
                    public void onSlide(float percent) {

                    }

                    @Override
                    public void onVisibilityChanged(int visibility) {

                    }
                })
                .withStartGravity(Gravity.BOTTOM)
                .withLoggingEnabled(true)
                .withGesturesEnabled(true)
                .withStartState(SlideUp.State.HIDDEN)
                .build();
        btnTest.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                slideUp.show();
                return false;
            }
        });
        lnContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                slideUp.hide();
                return false;
            }
        });
//        btnTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                slideUp.show();
//            }
//        });
    }

    private void allAboutLayout(Intent intent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = layoutInflater.inflate(R.layout.ovelay_window, null);
        mView.setFocusable(false);
        mView.setClickable(false);
        seekBar = (SeekBar) mView.findViewById(R.id.BrightBar);
        cResolver = getContentResolver();
        BrightnessControl(seekBar);
        View view = mView.findViewById(R.id.view);
        LinearLayout lnSlide = mView.findViewById(R.id.dragView);
        slideUp = new SlideUpBuilder(lnSlide)
                .withListeners(new SlideUp.Listener.Events() {
                    @Override
                    public void onSlide(float percent) {

                    }

                    @Override
                    public void onVisibilityChanged(int visibility) {

                    }
                })
                .withStartGravity(Gravity.BOTTOM)
                .withLoggingEnabled(true)
                .withGesturesEnabled(true)
                .withStartState(SlideUp.State.HIDDEN)
                .build();
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                slideUp.show();
                view.setVisibility(View.GONE);
                return false;
            }
        });
        lnSlide.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                slideUp.hide();
                view.setVisibility(View.VISIBLE);
                return false;
            }
        });

        //mLayout = (SlidingUpPanelLayout) mView.findViewById(R.id.sliding_layout);
        TextView tvDate, tvHour;
        imgBluetooth=mView.findViewById(R.id.imgBluetooth);
        imgPin=mView.findViewById( R.id.imgPin);
        imgMang=mView.findViewById(R.id.imgMang);
        imgWifi=mView.findViewById(R.id.imgWifi);
        imgLoca=mView.findViewById(R.id.imgLoca);
        imgSound=mView.findViewById(R.id.imgSound);
        imgRotate=mView.findViewById(R.id.imgRotate);
        imgSound.setVisibility(View.GONE);
        imgLoca.setVisibility(View.GONE);
        imgWifi.setVisibility(View.GONE);
        imgMang.setVisibility(View.GONE);
        imgRotate.setVisibility(View.GONE);
        rcvIconSetting = mView.findViewById(R.id.rcvIconSetting);
        rcvIconSetting.setLayoutManager(new GridLayoutManager(this, 4));
        int powifi = -1;
        int podata = -1;
        int pobluetooth = -1;
        int polocation = -1;
        int porotetion = -1;
        for (int i = 0; i < text1.size(); i++) {
            if (text1.get(i).contains("Wifi")) {
                powifi = i;
            } else if (text1.get(i).contains("Mobile data")) {
                podata = i;
            } else if (text1.get(i).contains("Bluetooth")) {
                pobluetooth = i;
            } else if (text1.get(i).contains("Location")) {
                polocation = i;
            } else if (text1.get(i).contains("Auto-rotate")) {
                porotetion = i;
            }
        }
        AudioManager audiomanage = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audiomanage.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        switch (am.getRingerMode()) {
            case AudioManager.RINGER_MODE_SILENT:
                Log.i("MyApp", "Silent mode");
                break;
            case AudioManager.RINGER_MODE_VIBRATE:
                Log.i("MyApp", "Vibrate mode");
                break;
            case AudioManager.RINGER_MODE_NORMAL:
                Log.i("MyApp", "Normal mode");
                break;
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
            imgBluetooth.setVisibility(View.VISIBLE);
        }
        if (isLocationEnabled(this)) {
            list.get(polocation).setImg(R.drawable.ic_location_e);
            imgLoca.setVisibility(View.VISIBLE);
        } else {
            list.get(polocation).setImg(R.drawable.ic_location_n);
        }
        if (getRotationScreenFromSettingsIsEnabled(this)) {
            list.get(porotetion).setImg(R.drawable.ic_auto_rotate_e);
            imgRotate.setVisibility(View.VISIBLE);

        } else {
            list.get(porotetion).setImg(R.drawable.ic_auto_rotate_n);
        }
        WifiManager manager1 = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (manager1.isWifiEnabled()) {
            WifiInfo wifiInfo = manager1.getConnectionInfo();
            if (wifiInfo != null) {
                NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
                if (state == NetworkInfo.DetailedState.CONNECTED || state == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
                    if (wifiEnabled) {
                        if (powifi != -1) {
                            list.get(powifi).setName(wifiInfo.getSSID());
                            list.get(powifi).setImg(R.drawable.ic_wifi_e);
                            imgWifi.setVisibility(View.VISIBLE);
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
//        mLayout.setAnchorPoint(0.4f);
//        mLayout.setClipPanel(true);
//
//        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
//            @Override
//            public void onPanelSlide(View panel, float slideOffset) {
//                Log.i("TAG", "onPanelSlide, offset " + slideOffset);
//                if (slideOffset == 0.0f) {
////                    mView.setClickable(false);
////                    mView.setFocusable(false);
////                    mView.setEnabled(false);
//                }
//            }
//
//            @Override
//            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
//                Log.i("TAG", "onPanelSlide, offset ");
//            }
//        });
//        mLayout.setFadeOnClickListener(null);
//        mLayout.setClickable(false);
//        mLayout.setFocusable(false);
//        mLayout.setFadeOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, "Hello", Toast.LENGTH_SHORT).show();
//              //  mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
//            }
//        });


    }

    public static void enableDisableView(View view, boolean enabled) {
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;

            for (int idx = 0; idx < group.getChildCount(); idx++) {
                enableDisableView(group.getChildAt(idx), enabled);
            }
        }
    }

    private void BrightnessControl(SeekBar seekBar) {
        seekBar.setMax(255);
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
                if (progress <= 20) {
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
                imgWifi.setVisibility(View.VISIBLE);

            } else {
                list.get(position).setImg(R.drawable.ic_wifi_n);
                list.get(position).setName("Wifi");
                imgWifi.setVisibility(View.GONE);
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
                imgBluetooth.setVisibility(View.VISIBLE);
            } else {
                list.get(position).setImg(R.drawable.ic_bluetooth_n);
                imgBluetooth.setVisibility(View.GONE);
                mBluetoothAdapter.disable();
            }
        } else if (text1.get(position).contains("Auto-rotate")) {
            if (list.get(position).getImg().equals(R.drawable.ic_auto_rotate_n)) {
                list.get(position).setImg(R.drawable.ic_auto_rotate_e);
                setRotationScreenFromSettings(this, true);
                imgRotate.setVisibility(View.VISIBLE);
            } else {
                list.get(position).setImg(R.drawable.ic_auto_rotate_n);
                setRotationScreenFromSettings(this, false);
                imgRotate.setVisibility(View.GONE);
            }
        } else if (text1.get(position).contains("Sync")) {
            if (list.get(position).getImg().equals(R.drawable.ic_sync_n)) {
                list.get(position).setImg(R.drawable.ic_sync_e);
            } else {
                list.get(position).setImg(R.drawable.ic_sync_n);
            }
        } else if (text1.get(position).contains("Do not")) {
            if (list.get(position).getImg().equals(R.drawable.ic_sync_n)) {
                list.get(position).setImg(R.drawable.ic_sync_e);
            } else {
                list.get(position).setImg(R.drawable.ic_sync_n);
            }
        } else if (text1.get(position).contains("Location")) {
            if (list.get(position).getImg().equals(R.drawable.ic_location_n)) {
                list.get(position).setImg(R.drawable.ic_location_e);
                imgLoca.setVisibility(View.VISIBLE);
//                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
             //   displayLocationSettingsRequest(this,activity);
                //turnlocation(this);
                //turnGPSOn();
            } else {
                list.get(position).setImg(R.drawable.ic_location_n);
                imgLoca.setVisibility(View.GONE);
                turnGPSOff();
            }
        } else if (text1.get(position).contains("Torch")) {
            if (list.get(position).getImg().equals(R.drawable.ic_torch_n)) {
                list.get(position).setImg(R.drawable.ic_torch_e);
                turnFlashlightOn();
            } else {
                list.get(position).setImg(R.drawable.ic_torch_n);
                turnFlashlightOff();
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

    private void turnGPSOn() {
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (!provider.contains("gps")) { //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);

            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);
        }
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL_IN_MILLISECONDS)
                .setFastestInterval(UPDATE_INTERVAL_IN_MILLISECONDS / 2);
        buildLocationSettingsRequest();

    }

    protected GoogleApiClient mGoogleApiClient;
    protected LocationRequest locationRequest;
    int REQUEST_CHECK_SETTINGS = 100;
    protected LocationManager locationManager;

    private void turnlocation(Context context) {
        LocationRequest request;
        request=new LocationRequest();
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(5000).setFastestInterval(1000);

        LocationSettingsRequest settingsRequest = new LocationSettingsRequest.Builder().addLocationRequest(request).build();
        LocationServices.getSettingsClient(context)
                .checkLocationSettings(settingsRequest).addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                Toast.makeText(context, "Dc roi", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            System.out.println("chua chua1111");
//
//            return;
//        }else {
//            System.out.println("chua chua");
//        }
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//        locationRequest = LocationRequest.create();
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setInterval(30 * 1000);
//        locationRequest.setFastestInterval(5 * 1000);
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
//                .addLocationRequest(locationRequest);
//        builder.setAlwaysShow(true);

    }
    private void displayLocationSettingsRequest(Context context,Activity activity) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);
        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i("TAG", "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i("TAG", "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult( activity, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i("TAG", "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i("TAG", "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });
    }
    protected void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }

    //
//    protected void checkLocationSettings() {
//        PendingResult<LocationSettingsResult> result =
//                LocationServices.SettingsApi.checkLocationSettings(
//                        mGoogleApiClient,
//                        mLocationSettingsRequest
//                );
//        result.setResultCallback(this);
//    }
    private void turnGPSOff() {
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if (provider.contains("gps")) { //if gps is enabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);
        }
    }

    public static void setRotationScreenFromSettings(Context context, boolean enabled) {
        try {
            if (Settings.System.getInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION) == 1) {
                Display defaultDisplay = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                Settings.System.putInt(context.getContentResolver(), Settings.System.USER_ROTATION, defaultDisplay.getRotation());
                Settings.System.putInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0);
            } else {
                Settings.System.putInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 1);
            }

            Settings.System.putInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, enabled ? 1 : 0);

        } catch (Settings.SettingNotFoundException e) {
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

    private void turnFlashlightOn() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                String cameraId = null;
                if (camManager != null) {
                    cameraId = camManager.getCameraIdList()[0];
                    camManager.setTorchMode(cameraId, true);
                }
            } catch (CameraAccessException e) {
                Log.e("TAG", e.toString());
            }
        } else {
            mCamera = Camera.open();
            parameters = mCamera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(parameters);
            mCamera.startPreview();
        }
    }

    private void turnFlashlightOff() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                String cameraId;
                camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                if (camManager != null) {
                    cameraId = camManager.getCameraIdList()[0]; // Usually front camera is at 0 position.
                    camManager.setTorchMode(cameraId, false);
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        } else {
            mCamera = Camera.open();
            parameters = mCamera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            mCamera.setParameters(parameters);
            mCamera.stopPreview();
        }
    }
    private String getFlashOnParameter() {
        List<String> flashModes = camera.getParameters().getSupportedFlashModes();

        if (flashModes.contains(FLASH_MODE_TORCH)) {
            return String.valueOf(FLASH_MODE_TORCH);
        } else if (flashModes.contains(FLASH_MODE_ON)) {
            return FLASH_MODE_ON;
        } else if (flashModes.contains(FLASH_MODE_AUTO)) {
            return FLASH_MODE_AUTO;
        }else {
            return "";
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}



