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
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ColorStateListDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.settingapp.BottomStatusBar.BottomStatusActivity;
import com.example.settingapp.R;
import com.example.settingapp.extra.ExtraActivity;
import com.example.settingapp.handles.HandlesActivity;
import com.example.settingapp.layout.Layout_Activity;
import com.example.settingapp.notifications.NotificationActivity;
import com.example.settingapp.tiles.TilesActivity;
import com.example.settingapp.colors.ColorsActivity;
import com.example.settingapp.sliders.SlidersActivity;
import com.example.settingapp.tiles.TilesActivity;
import com.example.settingapp.tilestyle.TileStylesActivity;
import com.example.settingapp.util.MyAccessibilityService;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu_nav;
    LinearLayout lnTiles, lnSlides, lnColors, lnTileStyle, lnHandles,lnLayout,lnExtra,lnBackgroundType,lnNotification,lnStatusbar;
    RelativeLayout rlConnect;
    Button btnTest,btn_ok;
    Context context;
    LinearLayout dragView;
    private int REQUEST_ACCESSIBILITY = 777;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(Color.parseColor("#f0f0f0"));// set status background white

        lnTiles = findViewById(R.id.lnTiles);
        lnSlides = findViewById(R.id.lnSlides);
        lnColors = findViewById(R.id.lnColors);
        btn_ok = findViewById(R.id.btn_ok);
        dragView=findViewById(R.id.dragView);
        rlConnect = findViewById(R.id.status_service);
        lnTileStyle = findViewById(R.id.lnTileStyles);
        lnHandles = findViewById(R.id.lnHandler);
        lnLayout = findViewById(R.id.lnLayout);
        lnExtra = findViewById(R.id.lnExtra);
        lnNotification = findViewById(R.id.lnNotification);
        lnStatusbar = findViewById(R.id.lnStatusbar);
        lnBackgroundType = findViewById(R.id.lnBackgroundType);
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
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
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
        rlConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acesspermission(getBaseContext());
            }
        });
        btnTest = findViewById(R.id.btnTest);
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
                Intent intent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(intent);

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

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id) {
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
                return true;
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

    private void openFloatingWindow(Context c) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_SETTINGS) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(c, FloatingWindow.class);
            c.stopService(intent);
            c.startService(intent);
        } else {
            askForPermission(android.Manifest.permission.WRITE_SETTINGS,1);
        }

    }
    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);

            } else {

                Log.d("TAG", "askForPermission: " + permission);

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }
    public static void youDesirePermissionCode(Activity context){
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
        }  else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                context.startActivityForResult(intent, 123);
            } else {
                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_SETTINGS}, 123);
            }
        }
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
        if (requestCode == REQUEST_ACCESSIBILITY && resultCode == RESULT_OK) {
            checkDrawOverlayPermission(this);
        } else if (requestCode == Overlay_REQUEST_CODE){
            if (Build.VERSION.SDK_INT >= 23) {
                if (Settings.canDrawOverlays(this)) {
                    openFloatingWindow(this);
                }
            } else {
                openFloatingWindow(this);
            }
        }else if (requestCode==123&& Settings.System.canWrite(this)){
            Intent intent = new Intent(this, FloatingWindow.class);
            this.stopService(intent);
            this.startService(intent);
        }

    }
}