package com.example.settingapp.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.settingapp.R;

public class RequestAccessibilityServiceActivity extends AppCompatActivity {

    private static final int REQUEST_ACCESSIBILITY = 777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.color_toolbar1, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.color_toolbar1));
        }
        setContentView(R.layout.activity_request_accessibility_service);

        findViewById(R.id.btn_active).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.samsung.accessibility.installed_service");
                System.out.println("mmmmmmmm///"+getPackageName());
                if (intent.resolveActivity(getPackageManager()) == null) {
                    intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                }
                try {
                    startActivityForResult(intent, REQUEST_ACCESSIBILITY);
                } catch (Exception e) {
                    startActivityForResult(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS), REQUEST_ACCESSIBILITY);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("back","ahihi");
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ACCESSIBILITY) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", "Enable");
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }
}