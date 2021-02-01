package com.example.miui_ify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import android.widget.ImageView;
import android.widget.Toast;

import com.example.settingapp.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Window window = getWindow();
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
//        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.white));// set status background white

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
                        Toast.makeText(MainActivity.this, "Home is Clicked", Toast.LENGTH_SHORT).show();break;
                    case R.id.ADB:
                        Toast.makeText(MainActivity.this, "Message is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.tasker_setting:
                        Toast.makeText(MainActivity.this, "Synch is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.playstore:
                        Toast.makeText(MainActivity.this, "Trash is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.twitter:
                        Toast.makeText(MainActivity.this, "Settings is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.telegram:
                        Toast.makeText(MainActivity.this, "Login is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.help_forum:
                        Toast.makeText(MainActivity.this, "Share is clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.help_translate:
                        Toast.makeText(MainActivity.this, "Rate us is Clicked",Toast.LENGTH_SHORT).show();break;
                    default:
                        return true;
                }
                return true;
            }

        });

    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}