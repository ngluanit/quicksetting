package com.example.miui_ify;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import com.example.settingapp.R;
import com.example.settingapp.tiles.TilesActivity;
import com.example.settingapp.colors.ColorsActivity;
import com.example.settingapp.sliders.SlidersActivity;
import com.example.settingapp.tiles.TilesActivity;
import com.example.settingapp.util.MyAccessibilityService;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu_nav;
    LinearLayout lnTiles, lnSlides, lnColors;
    RelativeLayout rlConnect;
    Button btnTest;
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
        rlConnect = findViewById(R.id.status_service);
        rlConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acesspermission(getBaseContext());
            }
        });
        btnTest=findViewById(R.id.btnTest);
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
            } catch (java.lang.Exception e){
                startActivityForResult(
                        new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS),
                        REQUEST_ACCESSIBILITY
                );
            }
        } else {
            btnTest.setVisibility(View.VISIBLE);
          //  Toast.makeText(this, "vo roi nhe", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ACCESSIBILITY && resultCode == RESULT_OK) {
            btnTest.setVisibility(View.VISIBLE);
        }
    }
}