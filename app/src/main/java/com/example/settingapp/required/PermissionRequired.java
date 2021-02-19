package com.example.settingapp.required;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.miui_ify.FloatingWindow;
import com.example.miui_ify.MainActivity;
import com.example.settingapp.R;
import com.example.settingapp.util.MyAccessibilityService;

public class PermissionRequired extends AppCompatActivity {

    ImageView imgBack,img_turnService,img_turnDraw;

    private boolean img_turnServiceShown = true;
    private boolean img_turnDrawShown = true;
    private int REQUEST_ACCESSIBILITY = 777;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_required);
        Window window = getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(Color.parseColor("#f3f3f3"));// set status background white

        imgBack = findViewById(R.id.imgBack);
        img_turnService = findViewById(R.id.img_turnService);
        img_turnDraw = findViewById(R.id.img_turnDraw);

        img_turnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((img_turnService != null) && (img_turnServiceShown)){
                    img_turnService.setImageResource(R.drawable.ic_switch_on);
                    img_turnServiceShown = false;
                    acesspermission(getBaseContext());

                }
                else {
                    if (img_turnService != null) img_turnService.setImageResource(R.drawable.ic_switch_off);
                    img_turnServiceShown = true;

                }
            }
        });

        img_turnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((img_turnDraw != null) && (img_turnDrawShown)){
                    img_turnDraw.setImageResource(R.drawable.ic_switch_on);
                    img_turnDrawShown = false;
                    acesspermission1(getBaseContext());

                }
                else {
                    if (img_turnDraw != null) img_turnDraw.setImageResource(R.drawable.ic_switch_off);
                    img_turnDrawShown = true;

                }
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PermissionRequired.this, MainActivity.class));
            }
        });
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
        }
    }

    private void acesspermission1(Context mContext) {
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
                youDesirePermissionCode(PermissionRequired.this);
//                openFloatingWindow(context);
            }
        } else {
            youDesirePermissionCode(PermissionRequired.this);
            //   openFloatingWindow(context);
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
           context.startActivity(new Intent(context, MainActivity.class));
//            Intent intent = new Intent(context, FloatingWindow.class);
//            context.stopService(intent);
//            context.startService(intent);
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
}