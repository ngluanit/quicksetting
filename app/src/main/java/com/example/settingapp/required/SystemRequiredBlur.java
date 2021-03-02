package com.example.settingapp.required;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.settingapp.R;

public class SystemRequiredBlur extends AppCompatActivity {
    RelativeLayout rlDevice_storage, rlCapture;
    ImageView img_turndevice, img_turnCapture;

    private boolean img_turndeviceShown = true;
    private boolean img_turnCaptureShown = true;
    private static final int REQUEST_CODE_SCREEN_RECORDING = 111;
    private MediaProjection mMediaProjection;
    private MediaProjectionManager mProjectionManager;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_required_blur);

        Window window = getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(Color.parseColor("#f0f0f0"));// set status background white

        rlDevice_storage = findViewById(R.id.rlDevice_storage);
        rlCapture = findViewById(R.id.rlCapture);
        img_turndevice = findViewById(R.id.img_turndevice);
        img_turnCapture = findViewById(R.id.img_turnCapture);

        rlDevice_storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((img_turndevice != null) && (img_turndeviceShown)) {
                    img_turndevice.setImageResource(R.drawable.ic_switch_on);
                    img_turndeviceShown = false;
                    isStoragePermission(SystemRequiredBlur.this);

                } else {
                    if (img_turndevice != null)
                        img_turndevice.setImageResource(R.drawable.ic_switch_off);
                    img_turndeviceShown = true;
                }
            }
        });
        rlCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((img_turnCapture != null) && (img_turnCaptureShown)) {
                    img_turnCapture.setImageResource(R.drawable.ic_switch_on);
                    img_turnCaptureShown = false;
                    mProjectionManager = (MediaProjectionManager) getSystemService
                            (Context.MEDIA_PROJECTION_SERVICE);
                    startActivityForResult(mProjectionManager.createScreenCaptureIntent(), REQUEST_CODE_SCREEN_RECORDING);

                } else {
                    if (img_turnCapture != null)
                        img_turnCapture.setImageResource(R.drawable.ic_switch_off);
                    img_turnCaptureShown = true;
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Write external storage permission required", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_SCREEN_RECORDING){
            mMediaProjection = mProjectionManager.getMediaProjection(resultCode, data);
        }
    }

    public boolean isStoragePermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
            }else {
                return true;
            }
        }
}