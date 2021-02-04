package com.example.settingapp.welcome

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.miui_ify.MainActivity
import com.example.settingapp.R
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.android.synthetic.main.activity_blur_background.*


class BlurBackgroundActivity : AppCompatActivity() {
    private val REQUEST_WRITE_PERMISSION=222
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blur_background)
        val window = window
        getWindow().decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark
        getWindow().statusBarColor = Color.parseColor("#f0f0f0") // set status background white
        imgTurnStorage.setOnClickListener {
            requestPermission()
        }
        imgBack.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java);
            startActivity(intent)
        }
    }
    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), REQUEST_WRITE_PERMISSION
            )
        }else{
            Toast.makeText(this,"Grand Storage",Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_WRITE_PERMISSION ->{
                if (grantResults.size>0&&permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this,"Grand Storage",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}