package com.example.settingapp.welcome

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.settingapp.R
import kotlinx.android.synthetic.main.activity_blur_background.*


class BlurBackgroundActivity : AppCompatActivity() {
    private val REQUEST_WRITE_PERMISSION=222
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blur_background)
        imgTurnStorage.setOnClickListener {
            requestPermission()
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