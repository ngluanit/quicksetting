package com.example.miui_ify;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.settingapp.BottomStatusBar.BottomStatusActivity;
import com.example.settingapp.R;
import com.example.settingapp.extra.ExtraActivity;
import com.example.settingapp.handles.HandlesActivity;
import com.example.settingapp.layout.Layout_Activity;
import com.example.settingapp.notifications.NotificationActivity;
import com.example.settingapp.required.PermissionRequired;
import com.example.settingapp.tiles.TilesActivity;
import com.example.settingapp.colors.ColorsActivity;
import com.example.settingapp.sliders.SlidersActivity;
import com.example.settingapp.tiles.TilesActivity;
import com.example.settingapp.tilestyle.TileStylesActivity;
import com.example.settingapp.util.MyAccessibilityService;
import com.example.settingapp.util.QSIntentService;
import com.example.settingapp.util.SharePref;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Method;
import java.util.List;

import static android.hardware.Camera.Parameters.FLASH_MODE_AUTO;
import static android.hardware.Camera.Parameters.FLASH_MODE_ON;
import static android.hardware.camera2.CameraMetadata.FLASH_MODE_TORCH;
import static android.provider.SyncStateContract.Columns.ACCOUNT_TYPE;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu_nav,img_turnservice;
    LinearLayout lnTiles, lnSlides, lnColors, lnTileStyle, lnHandles, lnLayout, lnExtra, lnBackgroundType, lnNotification, lnStatusbar;
    RelativeLayout rlConnect,status_service;
    TextView tv_service;
    Button btnTest, btn_ok;
    Context context;
    LinearLayout dragView;
    private final int REQUEST_ACCESSIBILITY = 777;
    SharedPreferences pref;
    private boolean img_turnserviceShown = true;
    int check = 0;

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharePref.setBooleanPref(this,"brightbar",true);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        Window window = getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(Color.parseColor("#f0f0f0"));// set status background white
        editor.putBoolean("first", true);
        editor.commit();
        TelephonyManager manager = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        String countryiso = manager.getSimCountryIso();
        String simOperator = manager.getSimOperator();
        String simOperatorName = manager.getSimOperatorName();
        int simState = manager.getSimState();
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!mNotificationManager.isNotificationPolicyAccessGranted()) {
                Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                startActivity(intent);
            }
        }

        lnTiles = findViewById(R.id.lnTiles);
        lnSlides = findViewById(R.id.lnSlides);
        lnColors = findViewById(R.id.lnColors);
        btn_ok = findViewById(R.id.btn_ok);
        dragView = findViewById(R.id.dragView);
        lnTileStyle = findViewById(R.id.lnTileStyles);
        lnHandles = findViewById(R.id.lnHandler);
        lnLayout = findViewById(R.id.lnLayout);
        lnExtra = findViewById(R.id.lnExtra);
        lnNotification = findViewById(R.id.lnNotification);
        lnStatusbar = findViewById(R.id.lnStatusbar);
        lnBackgroundType = findViewById(R.id.lnBackgroundType);
        img_turnservice = findViewById(R.id.img_turnservice);
        status_service = findViewById(R.id.status_service);
        tv_service = findViewById(R.id.tv_service);
        if (isAccessibilitySettingsOn(this) && Settings.canDrawOverlays(this)) {
            img_turnservice.setImageResource(R.drawable.ic_switch_on);
            img_turnserviceShown = false;
            status_service.setBackgroundResource(R.drawable.bg_service);
        } else {
            img_turnserviceShown = true;
            status_service.setBackgroundResource(R.drawable.bg_cardview1);
            tv_service.setTextColor(getColor(R.color.black));
        }

        img_turnservice.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint({"ResourceAsColor", "Range"})
            @Override
            public void onClick(View v) {
                if ((img_turnservice != null) && (img_turnserviceShown)) {
                    img_turnservice.setImageResource(R.drawable.ic_switch_on);
                    img_turnserviceShown = false;
                    status_service.setBackgroundResource(R.drawable.bg_service);
                    tv_service.setTextColor(getColor(R.color.white));
                    if (isAccessibilitySettingsOn(MainActivity.this) && Settings.canDrawOverlays(MainActivity.this)) {
                        openFloatingWindow(MainActivity.this);
                    } else {
                        startActivity(new Intent(MainActivity.this, PermissionRequired.class));
                    }

                } else {
                    if (img_turnservice != null)
                        img_turnservice.setImageResource(R.drawable.ic_switch_off);
                    img_turnserviceShown = true;
                    stopService(new Intent(MainActivity.this,FloatingWindow.class));
                    status_service.setBackgroundResource(R.drawable.bg_cardview1);
                    tv_service.setTextColor(getColor(R.color.black));
                }
            }
        });
        lnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
            }
        });

        lnStatusbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BottomStatusActivity.class));
            }
        });

        lnBackgroundType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog_backgroundtype);
                dialog.show();
            }
        });
        lnExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ExtraActivity.class));
                finish();
            }
        });
        lnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Layout_Activity.class));
            }
        });
        lnTileStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TileStylesActivity.class));
            }
        });
        lnHandles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HandlesActivity.class));
            }
        });

        lnSlides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SlidersActivity.class);
                startActivity(intent);

            }
        });
        lnColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, ColorsActivity.class);
//                startActivity(intent);
               // gotoSetting();
                //displayLocationSettingsRequest(context,MainActivity.this);
                try {

                   Object service = getSystemService("statusbar");
                    Class<?> statusBarManager = Class.forName("android.app.StatusBarManager");

                    // expands the notification bar into the quick settings mode
                    // - replace expandSettingsPanel with expandNotificationsPanel
                    // if you just want the normal notifications panel shown
                    Method expand = statusBarManager.getMethod("expandSettingsPanel");
                    expand.invoke(service);
                    Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
                    Method disable = statusbarManager.getMethod("disable", Integer.TYPE); //takes an int
                    disable.invoke(service, 2);
                } catch (Exception e) {
                    // do something else
                }
            }
        });
        lnTiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TilesActivity.class);
                startActivity(intent);

            }
        });
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);
        menu_nav = findViewById(R.id.menu_nav);

        menu_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(this);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                drawerLayout.closeDrawer(GravityCompat.START);
//                switch (id) {
//                    case R.id.theme_aplica:
//                        Toast.makeText(MainActivity.this, "Home is Clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.ADB:
//                        Toast.makeText(MainActivity.this, "Message is Clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.tasker_setting:
//                        Toast.makeText(MainActivity.this, "Synch is Clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.playstore:
//                        Toast.makeText(MainActivity.this, "Trash is Clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.twitter:
//                        Toast.makeText(MainActivity.this, "Settings is Clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.telegram:
//                        Toast.makeText(MainActivity.this, "Login is Clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.help_forum:
//                        Toast.makeText(MainActivity.this, "Share is clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.help_translate:
//                        Toast.makeText(MainActivity.this, "Rate us is Clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                    default:
//                        return true;
//                }
//                return true;
//            }
//
//        });

    }

    private boolean isAccessibilitySettingsOn(Context mContext) {
        try {
            int accessibilityEnabled = 0;
            String service =
                    mContext.getPackageName() + "/" + MyAccessibilityService.class.getCanonicalName();
            try {
                accessibilityEnabled = Settings.Secure.getInt(
                        mContext.getApplicationContext().getContentResolver(),
                        Settings.Secure.ACCESSIBILITY_ENABLED
                );
                Log.v("TAG", "accessibilityEnabled = $accessibilityEnabled");
            } catch (Settings.SettingNotFoundException e) {
                Log.e(
                        "TAG", "Error finding setting, default accessibility to not found: "
                                + e.getMessage()
                );
            }
            TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');
            if (accessibilityEnabled == 1) {
                Log.v("TAG", "***ACCESSIBILITY IS ENABLED*** -----------------");
                String settingValue = Settings.Secure.getString(
                        mContext.getApplicationContext().getContentResolver(),
                        Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
                );
                if (settingValue != null) {
                    mStringColonSplitter.setString(settingValue);
                    while (mStringColonSplitter.hasNext()) {
                        String accessibilityService = mStringColonSplitter.next();
                        Log.v(
                                "TAG",
                                "-------------- > accessibilityService :: $accessibilityService $service"
                        );
                        if (accessibilityService.equalsIgnoreCase(service)) {
                            Log.v(
                                    "TAG",
                                    "We've found the correct setting - accessibility is switched on!"
                            );
                            return true;
                        }
                    }
                }
            } else {
                Log.v("TAG", "***ACCESSIBILITY IS DISABLED***");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void acesspermission(Context mContext) {
//        Intent intent=new Intent(this, MaintwoActivity.class);
//        startActivity(intent);
        if (!isAccessibilitySettingsOn(mContext)) {
            Intent intent = new Intent("com.samsung.accessibility.installed_service");
            if (intent.resolveActivity(mContext.getPackageManager()) == null) {
                intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            }
            try {
                startActivityForResult(
                        intent,
                        REQUEST_ACCESSIBILITY
                );
            } catch (java.lang.Exception e) {
                startActivityForResult(
                        new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS),
                        REQUEST_ACCESSIBILITY
                );
            }
        } else {
            checkDrawOverlayPermission(mContext);
            //  Toast.makeText(this, "vo roi nhe", Toast.LENGTH_SHORT).show();
        }
    }

    public final static int Overlay_REQUEST_CODE = 251;

    public void checkDrawOverlayPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(context)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, Overlay_REQUEST_CODE);
            } else {
                youDesirePermissionCode(MainActivity.this);
//                openFloatingWindow(context);
            }
        } else {
            youDesirePermissionCode(MainActivity.this);
            //   openFloatingWindow(context);
        }
    }

    private void openFloatingWindow(Activity c) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + c.getPackageName()));

            startActivityForResult(intent, 123);
            System.out.println("zo day");
//                Intent intent = new Intent(context, FloatingWindow.class);
//                context.stopService(intent);
//                context.startService(intent);
        } else {
            ActivityCompat.requestPermissions(c, new String[]{Manifest.permission.WRITE_SETTINGS}, 123);
            System.out.println("zo day nua");
        }
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            } else {
                Log.d("TAG", "askForPermission: " + permission);
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }

    public static void youDesirePermissionCode(Activity context) {
        boolean permission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permission = Settings.System.canWrite(context);
        } else {
            permission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_SETTINGS) == PackageManager.PERMISSION_GRANTED;
        }
        if (permission) {
            Intent intent = new Intent(context, FloatingWindow.class);
            context.stopService(intent);
            context.startService(intent);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                context.startActivityForResult(intent, 123);
//                Intent intent = new Intent(context, FloatingWindow.class);
//                context.stopService(intent);
//                context.startService(intent);
            } else {
                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_SETTINGS}, 123);
            }
        }
    }
    int REQUEST_CHECK_SETTINGS = 100;
    private void displayLocationSettingsRequest(Context context,Activity activity) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this)
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
    private void gotoSetting(){
            Intent panelIntent = new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
            startActivityForResult(panelIntent, 545);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //do your code
            Intent intent = new Intent(this, FloatingWindow.class);
            this.stopService(intent);
            this.startService(intent);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ACCESSIBILITY ) {
            checkDrawOverlayPermission(this);
        } else if (requestCode == Overlay_REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (Settings.canDrawOverlays(this)) {
                    openFloatingWindow(this);
                }
            } else {
                openFloatingWindow(this);
            }
        } else if (requestCode == 123 && Settings.System.canWrite(this)) {
            Intent intent = new Intent(this, FloatingWindow.class);
            this.stopService(intent);
            this.startService(intent);
        }else if (requestCode==545){
            Toast.makeText(context, "Hello done", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        System.out.println("121123" + item.getItemId() + ".....zzzz");
        switch (item.getItemId()) {
            case R.id.theme_aplica:
                Toast.makeText(MainActivity.this, "Home is Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ADB:
                Toast.makeText(MainActivity.this, "Message is Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tasker_setting:
                Toast.makeText(MainActivity.this, "Synch is Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.playstore:
                Toast.makeText(MainActivity.this, "Trash is Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.twitter:
                Toast.makeText(MainActivity.this, "Settings is Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.telegram:
                Toast.makeText(MainActivity.this, "Login is Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.help_forum:
                Toast.makeText(MainActivity.this, "Share is clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.help_translate:
                Toast.makeText(MainActivity.this, "Rate us is Clicked", Toast.LENGTH_SHORT).show();
                break;
            default:
                return true;
        }
        return false;
    }

}